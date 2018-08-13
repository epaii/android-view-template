package wsview.ws.com.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
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
            public void onImage(ImageView imageView, String s) {
                Picasso.with(MainActivity.this).load(s).into(imageView);
                Log.i("xing", s);
            }

            @Override
            public void onChange(Context context, String s) {
                Toast.makeText(context, "onChange: " + s, Toast.LENGTH_SHORT).show();
            }
        });

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "张三");
        data.put("age", "19");
        data.put("link", "魅族 16 真牛X");
        data.put("img", "http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg");

//        WsViewTools.renderView(this, findViewById(R.id.lin), data);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject("{\"name\":\"张三\",\"age\":\"16\",\"sex\":\"男\",\"pro\":\"学生\",\"img\":\"http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WsViewTools.renderView(this, findViewById(R.id.lin), jsonObject);


    }


    public void fun(View view, HashMap<String, String> data) {
        Toast.makeText(this, "fun: " + data.get("name"), Toast.LENGTH_SHORT).show();
    }
}
