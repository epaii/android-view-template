package com.wenshi.view;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/3/17.
 */

public class WsViewInit {
    public interface IWsViewLisenter {

        public void onImage(ImageView imgview, String url);

        void onChange(Context context, String click_to_change);
    }

    public static IWsViewLisenter clickChange = null;

    public static void setWsViewLisenter(IWsViewLisenter l) {
        clickChange = l;
    }

    public static IWsViewLisenter getWsViewLisenter() {
        return clickChange;
    }
}
