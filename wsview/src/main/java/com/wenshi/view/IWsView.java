package com.wenshi.view;



/**
 * Created by mrren on 2017/3/1.
 */
public interface IWsView {


    void bindData(Object data);

    String []  getClick();

    void bindData(Object data, WsVIewClickListener listener);

    String getClassName();
}
