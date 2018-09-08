package com.wenshi.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;




public class WsTextView extends TextView implements IWsView {


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
    public void bindData(IKeyValue data) {
        this.setText(WsViewTools.praseString(textValue, data));

        clicks = WsViewTools.initAttrsByData((Activity) this.getContext(), data, clicks_tmp);
    }

    @Override
    public String[] getClick() {
        return  clicks==null?clicks_tmp:clicks;
    }

    @Override
    public void bindData(IKeyValue data, WsVIewClickListener listener) {
        bindData(data);
        WsViewTools.initClick( this,listener);
    }


    @Override
    public String getClassName() {
        return null;
    }


}
