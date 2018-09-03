package com.wenshi.view;


/**
 * Created by mrren on 2017/3/1.
 */
public interface IWsView {


    void bindData(IKeyValue data);

    String[] getClick();

    void bindData(IKeyValue data, WsVIewClickListener listener);

    String getClassName();
}
