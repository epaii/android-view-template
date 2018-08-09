package com.wenshi.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.HashMap;


public class WsLinearLayout extends   LinearLayout implements IWsView {

    private  IWsViewManager viewManager = null;
    private   String textValue="";
    TypedArray typedArray = null;
    private String[] clicks = null;
    private String[] clicks_tmp = null;

    public WsLinearLayout(Context context) {
        super(context);
    }

    public WsLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }

    public WsLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }
    private void initAttrs(Context context,AttributeSet attrs){


//        typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WsElement);
        typedArray = getContext().obtainStyledAttributes(attrs, WsViewTools.getWsAttrsIds(context));
        clicks_tmp = WsViewTools.initAttrs(this, context, typedArray);





    }

    @Override
    public void bindData(HashMap<String, String>  data) {

        clicks =   WsViewTools.initAttrsByData((Activity) this.getContext(),data,clicks_tmp);
    }

    @Override
    public String[] getClick() {
        return  clicks==null?clicks_tmp:clicks;
    }



    @Override
    public void bindData(HashMap<String, String> data,WsVIewClickListener listener) {
        bindData(data);
        WsViewTools.initClick(this,listener);
    }

    @Override
    public String getClassName() {
        return null;
    }


}
