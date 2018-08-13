package com.wenshi.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;

import test.wenshi.com.android_view_template.R;


public class WsTextView extends TextView implements IWsView {

    private IWsViewManager viewManager = null;
    private String textValue="";
    TypedArray typedArray = null;
    String[] clicks = null;
    private String[] clicks_tmp = null;

    public WsTextView(Context context) {
        super(context);
    }

    public WsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }

    public WsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }
    private void initAttrs(Context context, AttributeSet attrs){


       typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WsElement);

        clicks_tmp = WsViewTools.initAttrs(this, context, typedArray);

         textValue = typedArray.getString(R.styleable.WsElement_wsValue);

        Log.e("lmjTag1",textValue+"");

    }

    @Override
    public void bindData(HashMap<String, String> data) {

        Log.e("textValue", "bindData: "+textValue+data.toString() );
        if(textValue!=null && textValue.length()>0)
        {
             this.setText(WsViewTools.praseString(textValue,data));

        }
        clicks =   WsViewTools.initAttrsByData((Activity) this.getContext(),data,clicks_tmp);
    }

    /**
     * JSON 绑定
     * @param jsonObject
     */
    @Override
    public void bindData(JSONObject jsonObject) {
        if(textValue!=null && textValue.length()>0)
        {
            this.setText(WsViewTools.praseString(textValue,jsonObject));

        }
        clicks =   WsViewTools.initAttrsByData((Activity) this.getContext(),jsonObject,clicks_tmp);
    }

    @Override
    public String[] getClick() {
        return  clicks==null?clicks_tmp:clicks;
    }



    @Override
    public void bindData(HashMap<String, String> data, WsVIewClickListener listener) {
        bindData(data);

        WsViewTools.initClick( this,listener);

    }

    /**
     * JSON 绑定
     * @param jsonObject
     * @param listener
     */
    @Override
    public void bindData(JSONObject jsonObject, WsVIewClickListener listener) {
        bindData(jsonObject);

        WsViewTools.initClick( this,listener);
    }

    @Override
    public String getClassName() {
        return null;
    }


}
