package com.wenshi.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import test.wenshi.com.android_view_template.R;


public class WsImageView extends ImageView implements IWsView {


    private String textValue = "";
    TypedArray typedArray = null;
    String[] clicks = null;
    private String[] clicks_tmp = null;

    public WsImageView(Context context) {
        super(context);

    }


    public WsImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public WsImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {


        typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WsElement);
        clicks_tmp = WsViewTools.initAttrs(this, context, typedArray);

        textValue = typedArray.getString(R.styleable.WsElement_wsValue);

    }


    @Override
    public void bindData(IKeyValue data) {
        if (textValue != null && textValue.length() > 0) {

            String imgurl = WsViewTools.praseString(textValue, data);
            Log.i("xing", imgurl);
            WsViewInit.getWsViewLisenter().onImage(this, imgurl);

        }
        clicks = WsViewTools.initAttrsByData((Activity) this.getContext(), data, clicks_tmp);
    }

    @Override
    public String[] getClick() {
        return clicks == null ? clicks_tmp : clicks;
    }

    @Override
    public void bindData(IKeyValue data, WsVIewClickListener listener) {
        bindData(data);
        WsViewTools.initClick(this, listener);
    }


    @Override
    public String getClassName() {
        return null;
    }


}
