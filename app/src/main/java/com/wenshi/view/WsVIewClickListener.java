package com.wenshi.view;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


public class WsVIewClickListener implements View.OnClickListener {

    private Context context;

    private HashMap<String, Method> contextMothes = new HashMap<>();

    public WsVIewClickListener(Context context) {
        this.context = context;

        Method[] modes = context.getClass().getMethods();
        for (Method mod : modes) {
            contextMothes.put(mod.getName(), mod);
        }
    }

    @Override
    public void onClick(View v) {
        Log.e("onClick", "onClick");
        IWsView view = (IWsView) v;
        String[] t = view.getClick();


        String click_to_change = t[0];
        if (click_to_change != null && click_to_change.length() > 0) {

            if (click_to_change.indexOf("://") == -1) {
                String[] s = click_to_change.split("\\?");

                click_to_change = "credCmd://?credCmd=1&_pre=1&_a=" + s[0];
                if (s.length == 2) {
                    click_to_change = click_to_change + "&" + s[1];
                }

            }
            WsViewInit.getWsViewLisenter().onChange(context, click_to_change);
            Log.e("click_to_change", "" + click_to_change);
        }

        String click_to_function = t[1];
        if (click_to_function != null && click_to_function.length() > 0) {

            if (click_to_function.indexOf("://") == -1) {
                String[] s = click_to_function.split("\\?");
                if (s[0].length() > 0) {
                    Method method = null;
                    try {
                        method = context.getClass().getMethod(s[0], new Class[]{View.class, HashMap.class});

                        if (method != null) {
                            method.invoke(context, v, s.length > 1 ? strToObject(s[1]) : new HashMap<>());
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                }
            }
            Log.e("click_to_function", "" + click_to_change);
        }
    }

    private HashMap<String, String> strToObject(String str) {
        HashMap<String, String> out = new HashMap<String, String>();
        StringBuffer stringBuffer = new StringBuffer();
        for (String item : str.split("&")) {
            String[] tmp = item.split("=");
            if (tmp.length == 2) {
                out.put(tmp[0].trim(), tmp[1].trim());
            }
        }
        return out;

    }

}
