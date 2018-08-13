package test.wenshi.com.android_view_template;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wenshi.view.WsViewInit;
import com.wenshi.view.WsViewTools;

import org.json.JSONException;
import org.json.JSONObject;

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
                Toast.makeText(context, ""+click_to_change, Toast.LENGTH_SHORT).show();
            }
        });

        HashMap<String,String> data = new HashMap<>();
        data.put("name","张三");
        data.put("age","19");
//        WsViewTools.renderView(this,findViewById(R.id.activity_main),data);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject("{\"name\":\"张三\",\"age\":\"16\",\"sex\":\"男\",\"pro\":\"学生\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WsViewTools.renderView(this,findViewById(R.id.activity_main), jsonObject);


    }

    public void ageclick(View view,HashMap<String,String> data)
    {
        Log.e("rlr",data.get("type")+":"+data.get("name"));
        Toast.makeText(this, ""+data.get("type")+":"+data.get("name"), Toast.LENGTH_SHORT).show();
    }
}
