package com.wenshi.view;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mrren on 2017/3/1.
 */
public interface IWsViewManager {

      void addView(IWsView view);

      void renderView(HashMap<String, String> data);

      void addItemView(IWsView view);

      void renderItemView(HashMap<String, String> data);

      void clearAllItemView();

      ArrayList<IWsView> getListItems();
}
