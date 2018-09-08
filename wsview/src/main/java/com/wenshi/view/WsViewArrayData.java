package com.wenshi.view;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.HashMap;

public class WsViewArrayData {

    public static ArrayList<IKeyValue> from(ArrayList<HashMap<String, String>> data) {

        WsViewArrayData tmp = new WsViewArrayData();
        tmp.data(data);
        return tmp.toArray();
    }

    public static ArrayList<IKeyValue> from(JSONArray data) {

        WsViewArrayData tmp = new WsViewArrayData();
        tmp.data(data);
        return tmp.toArray();
    }

    private Object data;

    public void data(ArrayList<HashMap<String, String>> data) {
        this.data = data;

    }

    public void data(JSONArray data) {
        this.data = data;

    }

    public ArrayList<IKeyValue> toArray() {
        ArrayList<IKeyValue> out = new ArrayList<IKeyValue>();
        if (data instanceof ArrayList) {
            ArrayList<HashMap<String, String>> tem = (ArrayList<HashMap<String, String>>) data;
            for (int i = 0; i < tem.size(); i++) {
                out.add(WsViewMapData.from(tem.get(i)));
            }
            return out;

        } else if (data instanceof JSONArray) {
            JSONArray tem = (JSONArray) data;
            for (int i = 0; i < tem.length(); i++) {
                try {
                    out.add(WsViewMapData.from(tem.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return out;
        }
        return out;
    }


}
