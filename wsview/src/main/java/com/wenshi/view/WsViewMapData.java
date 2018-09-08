package com.wenshi.view;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class WsViewMapData implements IKeyValue {

    public static IKeyValue from(HashMap<String, String> data) {

        WsViewMapData tmp = new WsViewMapData();
        tmp.data(data);
        return tmp;
    }

    public static IKeyValue from(JSONObject data) {

        WsViewMapData tmp = new WsViewMapData();
        tmp.data(data);
        return tmp;
    }

    private Object data;

    public void data(HashMap<String, String> data) {
        this.data = data;

    }

    public void data(JSONObject data) {
        this.data = data;

    }

    public ArrayList<IKeyValue> getArray(String key) {
        if (data instanceof JSONObject) {
            return WsViewArrayData.from(doget(key, JSONArray.class));
        } else {
            return new ArrayList<IKeyValue>();
        }

    }

    public IKeyValue getData(String key) {
        if (data instanceof JSONObject) {
            return WsViewMapData.from(doget(key, JSONObject.class));
        } else {
            return null;
        }

    }

    @Override
    public boolean containsKey(String key) {
        Log.e("rlr", key);
        if (data instanceof HashMap) {

            return ((HashMap) data).containsKey(key);
        } else if (data instanceof JSONObject) {

            String[] strs = key.split("\\.");

            JSONObject tmp = (JSONObject) data;
            int i = 0;
            if (strs.length > 0) {
                for (String str : strs) {

                    i++;
                    if (!tmp.has(str)) {
                        return false;
                    }
                    try {
                        if (i == strs.length) {

                            return true;
                        } else
                            tmp = tmp.getJSONObject(str);

                    } catch (JSONException e) {
                        // e.printStackTrace();
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    @Override
    public String get(String key) {
        return doget(key, String.class);
    }

    private <T> T doget(String key, Class<T> cls) {
        if (data instanceof HashMap) {

            return (T) ((HashMap<String, String>) data).get(key);
        } else if (data instanceof JSONObject) {
            Log.e("rlr", key);

            String[] strs = key.split("\\.");
            JSONObject tmp = (JSONObject) data;
            if (strs.length > 0) {
                int i = 0;
                for (String str : strs) {
                    i++;
                    if (!tmp.has(str)) {
                        return (T) "";
                    }
                    try {
                        if (i == strs.length) {

                            if (cls.equals(String.class))
                                return (T) tmp.getString(str);
                            else if (cls.equals(JSONObject.class))
                                return (T) tmp.getJSONObject(str);
                            else if (cls.equals(JSONArray.class))
                                return (T) tmp.getJSONArray(str);
                        } else
                            tmp = tmp.getJSONObject(str);
                    } catch (JSONException e) {

                        return null;
                    }
                }
                return (T) tmp;
            } else {
                return (T) "";
            }

        }
        return (T) "";
    }
}
