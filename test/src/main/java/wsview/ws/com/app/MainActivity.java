package wsview.ws.com.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wenshi.view.WsViewTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "张三");
        data.put("age", "19");
        data.put("link", "魅族 16 真牛X");
        data.put("pro", "学生");
        data.put("img", "http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg");

        JSONObject jsonObject = null;
        try {
//            jsonObject = new JSONObject("{\"name\":\"张三\",\"age\":\"16\",\"sex\":\"男\",\"pro\":\"学生\",\"img\":\"http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg\"}");
            jsonObject = new JSONObject("{\"people\":{\"firstName\": \"Brett\",\"lastName\":\"McLaughlin\"},\"age\":\"110岁\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //绑定数据
        WsViewTools.renderView(this, findViewById(R.id.lin), jsonObject);



    }


    public void fun(View view, HashMap<String, String> data) {
        Toast.makeText(this, "fun: " + data.get("name"), Toast.LENGTH_SHORT).show();
    }
}
