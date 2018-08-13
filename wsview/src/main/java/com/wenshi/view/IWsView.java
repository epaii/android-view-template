package com.wenshi.view;



import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by mrren on 2017/3/1.
 */
public interface IWsView {

    void bindData(HashMap<String, String> data);

    void bindData(JSONObject jsonObject);

    String[]  getClick();

    void bindData(HashMap<String, String> data, WsVIewClickListener listener);

    void bindData(JSONObject jsonObject, WsVIewClickListener listener);

    String getClassName();
}
