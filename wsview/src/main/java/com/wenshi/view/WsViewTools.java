package com.wenshi.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import test.wenshi.com.android_view_template.R;

public class WsViewTools {


    public static String praseString(final Activity context, String template, Object tokens){
        template = praseString(template, tokens);
        template = replace(template, "\\{_GET_(.*?)\\}", new IOnFindKey() {
            @Override
            public String onFindkey(String key) {
                if (context.getIntent().hasExtra(key))

                {
                    return context.getIntent().getStringExtra(key);
                } else {
                    return "";
                }
            }
        });

        return template;
    }

    public interface IOnFindKey {
        String onFindkey(String key);
    }

    public static String replace(String template, String patternString, IOnFindKey onfind) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        //两个方法：appendReplacement, appendTail
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {


            matcher.appendReplacement(sb, onfind.onFindkey(matcher.group(1)));

        }
        matcher.appendTail(sb);

        //out: Garfield really needs some coffee.
        System.out.println(sb.toString());

        //对于特殊含义字符"\","$"，使用Matcher.quoteReplacement消除特殊意义
        matcher.reset();
        return sb.toString();
    }

    public static String praseString(String template, final Object tokens) {
        if (tokens == null) {
            return template;
        }
        if(tokens instanceof HashMap){

            if (template.indexOf("{") == -1 && ((HashMap)tokens).containsKey(template)) {
                return (String) ((HashMap)tokens).get(template);
            }

            template = replace(template, "\\{(.*?)\\}", new IOnFindKey() {
                @Override
                public String onFindkey(String key) {
                    if (((HashMap)tokens).containsKey(key)) {
                        return (String) ((HashMap)tokens).get(key);
                    } else {
                        return "";
                    }
                }
            });

        } else if(tokens instanceof JSONObject){
            final Iterator keys = ((JSONObject)tokens).keys();
            try {
                while (keys.hasNext() && template.indexOf("{") == -1) {
                    String key = (String) keys.next();
                    if (key.equals(template)) {
                        String data = (String)((JSONObject)tokens).get(template);
                        Log.i("JSONdata", "data: " + data);
                        return data;
                    }
                    Log.i("JSONdata", "遍历次数: ~~~~~~~~~");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            template = replace(template, "\\{(.*?)\\}", new IOnFindKey() {
                @Override
                public String onFindkey(String mkey) {
                    final Iterator keys = ((JSONObject)tokens).keys();
                    String data = "";
                    try {
                        while (keys.hasNext()) {
                            String key = (String) keys.next();
                            if (key.equals(mkey)) {
                                data = (String) ((JSONObject)tokens).get(mkey);
                                Log.i("JSONdata", "data: " + data);
                                return data;
                            }
//                        Log.i("JSONdata", "遍历次数: ~~~~~~~~~");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return data;
                }

            });

        }

        template = replace(template, "\\{_G_(.*?)\\}", new IOnFindKey() {
            @Override
            public String onFindkey(String key) {
                //
                return key;
            }
        });

        return template;

    }


    public static String[] initAttrs(IWsView v, Context context, TypedArray typedArray) {
        if (context instanceof IWsViewManager) {

            if (!typedArray.getBoolean(R.styleable.WsElement_isInList, false)) {
                ((IWsViewManager) context).addView(v);
            } else {
                ((IWsViewManager) context).addItemView(v);
            }

        }

        String click_to_change = typedArray.getString(R.styleable.WsElement_wsClickToChange);
        String click_to_function = typedArray.getString(R.styleable.WsElement_wsClickToFunction);
        String wsShowIf = typedArray.getString(R.styleable.WsElement_wsShowIf);
        String wsHideIf = typedArray.getString(R.styleable.WsElement_wsHideIf);

        return new String[]{click_to_change, click_to_function, wsShowIf, wsHideIf};
    }

    public static String[] initAttrsByData(Activity context,Object data, String[] attrs) {

        String[] attrsout = new String[attrs.length];
        if (data == null) return attrs;
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] != null && attrs[i].length() > 0) {
                attrsout[i] = praseString(context, attrs[i], data);
            }
        }

        return attrsout;
    }

    public static void initClick(IWsView view, WsVIewClickListener listener) {
        String[] clicks = view.getClick();

        String click_to_change = clicks[0];
        String click_to_function = clicks[1];

        for (String tetm : clicks) {
            Log.e("initClick", "initClick: " + tetm);
        }


        if (click_to_change != null || click_to_function != null) {
            ((View) view).setClickable(true);
            ((View) view).setOnClickListener(listener);
        }


        if (clicks.length >= 3) {

            if (clicks[2] != null && praseStringToboolean(clicks[2])) {
                ((View) view).setVisibility(View.VISIBLE);
            }
        }
        if (clicks.length >= 4) {
            if (clicks[3] != null && praseStringToboolean(clicks[3])) {
                ((View) view).setVisibility(View.GONE);
            }
        }

    }


    public static Boolean praseStringToboolean(String str) {
        String[] tmp = null;
        boolean fei = false;


        if (str.startsWith("!")) {
            tmp = str.substring(1).split(".equals");
            fei = true;
        } else {
            tmp = str.split(".equals");
        }

        if (tmp[0].equals("login")) {
            //返回是否登录
        }
        if (tmp.length == 2) {
            tmp[1] = tmp[1].replace("(", "").replace(")", "");
            Log.e("boolen", tmp[0] + "and" + tmp[1]);
            boolean is = tmp[0].equalsIgnoreCase(tmp[1]);
            return fei ? !is : is;
        }
        return false;
    }

    public static void renderView(Context context, View view,Object data) {
        ArrayList<IWsView> viewlistemp = WsViewTools.getWsViews(view);
        int l = viewlistemp.size();
        WsVIewClickListener listener = new WsVIewClickListener(context);
        for (int i = 0; i < l; i++) {
            viewlistemp.get(i).bindData(data, listener);
        }
    }

    public static ArrayList<IWsView> getWsViews(View view) {
        if (view == null) {
            return null;
        }
        if (view.getTag(R.id.tag_1000) != null) {
            return (ArrayList<IWsView>) view.getTag(R.id.tag_1000);
        }
        ArrayList<IWsView> list = new ArrayList<>();
        if (view instanceof ViewGroup) {
            getWsViews((ViewGroup) view, list);
        } else if (view instanceof IWsView) {
            list.add((IWsView) view);
        }
//        view.setTag(WsViewTools.getResource(view.getContext(), "tag_1000", "id"), list);

        return list;

    }

    private static void getWsViews(ViewGroup viewGroup, ArrayList<IWsView> list) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof IWsView) {
                list.add((IWsView) view);
            }
            if (view instanceof ViewGroup) {

                getWsViews((ViewGroup) view, list);
            }
        }
    }

    public static int getResource(Context context, String name, String type) {
        if (type.equals("styleable")) {
            if (name.equals("WsElement_wsValue")) {
                return 0;
            } else if (name.equals("WsElement_wsClickToChange")) {
                return 1;

            } else if (name.equals("WsElement_wsClickToFunction")) {
                return 2;

            } else if (name.equals("WsElement_isInList")) {
                return 3;

            } else if (name.equals("WsElement_wsShowIf")) {
                return 3;

            } else if (name.equals("WsElement_wsHideIf")) {
                return 4;

            } else if (name.equals("WsElement_wsListViewId")) {
                return 6;

            }
        }
        try

        {
            String packageName = context.getPackageName();

            return context.getResources().getIdentifier(name, type, packageName);
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }
        return -1;
    }
}
