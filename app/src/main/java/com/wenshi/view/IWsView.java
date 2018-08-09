package com.wenshi.view;



import java.util.HashMap;


/**
 * Created by mrren on 2017/3/1.
 */
public interface IWsView {

    void bindData(HashMap<String, String> data);

    String []  getClick();

    void bindData(HashMap<String, String> data, WsVIewClickListener listener);

    String getClassName();
}
