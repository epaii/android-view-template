package test.wenshi.com.android_view_template;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.wenshi.view.WsViewInit;
import com.wenshi.view.WsViewTools;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WsViewInit.setWsViewLisenter(new WsViewInit.IWsViewLisenter() {
            @Override
            public void onImage(ImageView imgview, String url) {

            }

            @Override
            public void onChange(Context context, String click_to_change) {
                Log.e("rlr",click_to_change);
            }
        });

        HashMap<String,String> data = new HashMap<>();
        data.put("name","张三");
        data.put("age","19");
        WsViewTools.renderView(this,findViewById(R.id.activity_main),data);
    }

    public void ageclick(View view,HashMap<String,String> data)
    {
        Log.e("rlr",data.get("type")+":"+data.get("name"));
    }
}
