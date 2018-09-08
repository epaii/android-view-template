package com.wenshi.view;

import java.util.ArrayList;

public interface IKeyValue {
    boolean containsKey(String key);

    String get(String key);
    ArrayList<IKeyValue> getArray(String key);
    IKeyValue getData(String key);
}
