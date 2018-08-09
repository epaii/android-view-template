package com.wenshi.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;


public class WsTextView extends TextView  implements IWsView {

    private IWsViewManager viewManager = null;
    private   String textValue="";
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
    private void initAttrs(Context context,AttributeSet attrs){


//       typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WsElement);
        typedArray = getContext().obtainStyledAttributes(attrs, WsViewTools.getWsAttrsIds(context));

        clicks_tmp = WsViewTools.initAttrs(this, context, typedArray);

//         textValue = typedArray.getString(R.styleable.WsElement_wsValue);
        textValue = typedArray.getString(WsViewTools.getResource(context,"WsElement_wsValue","styleable"));

        Log.e("lmjTag1",textValue+"");

    }

    @Override
    public void bindData(HashMap<String, String>  data) {

        Log.e("textValue", "bindData: "+textValue+data.toString() );
        if(textValue!=null && textValue.length()>0)
        {
             this.setText(WsViewTools.praseString(textValue,data));

        }
        clicks =   WsViewTools.initAttrsByData((Activity) this.getContext(),data,clicks_tmp);
    }

    @Override
    public String[] getClick() {
        return  clicks==null?clicks_tmp:clicks;
    }



    @Override
    public void bindData(HashMap<String, String> data,WsVIewClickListener listener) {
        bindData(data);

        Log.e("rlr","binddata");
        WsViewTools.initClick( this,listener);

    }




    @Override
    public String getClassName() {
        return null;
    }


}
