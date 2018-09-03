package wsview.ws.com.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wenshi.view.WsViewInit;

/**
 * Created by Administrator on 2018/8/14.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("rlr", "start");
        WsViewInit.setWsViewLisenter(new WsViewInit.IWsViewLisenter() {
            @Override
            public void onImage(ImageView imgview, String url) {
                if (url != null) {
                    Picasso.with(getBaseContext()).load(url).into(imgview);
                }
                Log.i("xing", url);
            }

            @Override
            public void onChange(Context context, String click_to_change) {
                Toast.makeText(context, "onChange: " + click_to_change, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
