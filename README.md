
#模板引擎说明文档
*******************************************************************************
+ 什么是模板引擎？

  模板引擎是以业务逻辑层和表现层分离为目的的，将规定格式的模板代码转换为业务数据的算法实现。

+ 为什么要用到模板引擎？

   **举例：** 在我们做开发的时候，经常需要根据后台返回的数据来给控件赋值。
   通常我们是这样做的 ↓
   ```
   <!--xml中-->
    <TextView
           android:id="@+id/mTextView"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:text="我是textview"/>
   ```
   ```
   //代码中
   TextView mTextView = findViewById(R.id.mTextView);

    mTextView.setText(datas.get("key"));
   ```
   这样做 也非常规范、也非常正确、也非常没有毛病。
   但是呢！在我们开发的时候不可能就只有这一两个控件让你赋值吧...
   ```
    public void initView(View view) {
        rl_gonggao = (RelativeLayout) view.findViewById(R.id.rl_gonggao);
        rollPagerView = (RollPagerView) view.findViewById(R.id.rollPagerView);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        gridView = (MyGridView) view.findViewById(R.id.gridView);
        search_edit = (EditText) view.findViewById(R.id.search_edit);
        tv_gonggao = (TextView) view.findViewById(R.id.tv_gonggao);
        tv_xinpintuijian = (TextView) view.findViewById(R.id.tv_xinpintuijian);
        tv_cainixihuan = (TextView) view.findViewById(R.id.tv_cainixihuan);
        tv_fl1 = (TextView) view.findViewById(R.id.tv_fl1);
        tv_fl2 = (TextView) view.findViewById(R.id.tv_fl2);
        tv_fl3 = (TextView) view.findViewById(R.id.tv_fl3);
        tv_fl4 = (TextView) view.findViewById(R.id.tv_fl4);
        tv_quanbu = (TextView) view.findViewById(R.id.tv_quanbu);
        img_fl1 = (ImageView) view.findViewById(R.id.img_fl1);
        img_fl2 = (ImageView) view.findViewById(R.id.img_fl2);
        img_fl3 = (ImageView) view.findViewById(R.id.img_fl3);
        img_fl4 = (ImageView) view.findViewById(R.id.img_fl4);
        img_quanbu = (ImageView) view.findViewById(R.id.img_quanbu);
        img_dalibao = (ImageView) view.findViewById(R.id.img_dalibao);
        rl_shenghuo = (RelativeLayout) view.findViewById(R.id.rl_shenghuo);
        rl_shuma = (RelativeLayout) view.findViewById(R.id.rl_shuma);
        rl_shipin = (RelativeLayout) view.findViewById(R.id.rl_shipin);
        rl_fuzhuang = (RelativeLayout) view.findViewById(R.id.rl_fuzhuang);
        rl_quanbu = (RelativeLayout) view.findViewById(R.id.rl_quanbu);

        bg_content = (ScrollView) view.findViewById(R.id.bg_content);
        bar = (LinearLayout) view.findViewById(R.id.bar);

    }
   ```
   ↑ 这才是我们实际开发时的情况...
   我们的大部分工作都是在 findViewById()  ,  mTextView.setText();
   大大的降低了工作效率，代码屯余量高，浪费时间，而且还影响心情。

   那我们可不可以有个什么东西，替我们干这些工作呢？ &#160;
   答案是肯定的，这就是我们的 **模板引擎** 。
   我们只需要在控件属性里设置一个key，然后就可以自动绑定数据，省时省力  提高效率！

   ************************************************************************

+ 先来看看简单用法
   ```
    <!--xml中-->
    <com.wenshi.view.WsTextView
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           ws:wsValue="name" />
   ```
   ```
   //代码中
    HashMap<String ,String> data = new HashMap<>();
           data.put("name","张三");
           data.put("age","19岁");

    WsViewTools.renderView(this,findViewById(R.id.acv),data);
   ```
   就是这么简单，我们只需要在Activity中绑定一下数据：
   `WsViewTools.renderView(this,findViewById(R.id.acv),data);`
   xml中 `ws:wsValue="name"` 。这样就可以把“张三”直接设置到对应的TextView中。

   是不是特别简单！ 当然这样还不够

   ************************************************************************

#下面我们来说说这个模板引擎的完整用法 ↓

  + 前期准备：
      1. 在自己的 *Application* 中的 *onCreate* 方法中定义：
          ```
             WsViewInit.setWsViewLisenter(new WsViewInit.IWsViewLisenter() {
                 @Override
                 public void onImage(ImageView imgview, String url) {
                  //这里是你对图片的处理方式，自己定义，我这里是演示直接显示。
                  Picasso.with(context).load(url).into(imgview);
                 }

                 @Override
                 public void onChange(Context context, String click_to_change) {
                     //这里是你对点击事件的处理方式，自己定义，我这里是演示打印。
                     // "click_to_change" 是后台某一字段的数据
                    System.out.println(click_to_change);
                 }
             });

     2. 在Activity中绑定数据：
         ```
            /**
              *第一个参数 Context
              *第二个参数 View 要绑定的模板
              *第三个参数 HashMap<String, String> data 你的数据
              */
            WsViewTools.renderView(this, findViewById(R.id.acv), data);
        ```
+ 接下来就是绑定数据了：

   **先模拟数据：**
```
        HashMap<String ,String> data = new HashMap<>();
        data.put("name","张三");
        data.put("age","19岁");
        data.put("link","http://www.baidu.com");
        data.put("img", "https://imgsa.baidu.com/exp/w=480/sign=61953a69ab345982c58ae49a3cf4310b/95eef01f3a292df516b60208ba315c6034a873b2.jpg");
        data.put("isShow","1");
        data.put("isHide","0");

```
   +  ### **TextView:**
 ```
         <com.wenshi.view.WsTextView
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             ws:wsHideIf="{isHide}.equals(0)"
             ws:wsShowIf="{isShow}.equals(1)"
             ws:wsClickToChange="link"
             ws:wsValue="name" />
 ```
设置值就是： ` ws:wsValue=" 对应的key "   `
点击事件 ：   `  ws:wsClickToChange=" 对应的key "`
判断隐藏或显示： `ws:wsShowIf="{对应的key}.equals(1)"  `
 如果为1就显示，终于不用再代码中判断了，非常方便！

   1. 这个点击的方式就是把“link”的值，传到咱们第一步*Application* 中的 *onChange* 回调方法里，
然后你自己根据需求进行解析处理。上面的代码为了演示打印了一下，当我点击TextView时，
会打印出 "http://www.baidu.com"。

   2. 还有一种点击方式： `ws:wsClickToFunction="fun?click={name}"`
```
  //代码中
    public void fun(View view, HashMap<String, String> data) {
        //Toast出来的值是：  fun: 张三
        Toast.makeText(this, "fun: " + data.get("click"), Toast.LENGTH_SHORT).show();
    }
```
解释一下：
`"fun?click={name}&age={age}&img={img}" ` 这是可以拼多个值的。
"fun?click={name}"  &#160;    ==>>   &#160;   方法名 ？ 要获取数据的key = {HashMap中的key}
data.get("要获取数据的key")，这样我们就可以直接拿到值，不用再去弄个全局变量什么的来回传。

**这样就做到了业务逻辑层和表现层的分离。**


   + ### **ImageView：**
和*TextView*的用法一样，属性值填上*key*，在*Application* 中的 *onImage* 回调方法里进行处理，
当然怎么处理是你的事儿了，跟我没关系，我这只是演示。
```
<com.wenshi.view.WsImageView
        android:layout_width="match_parent"
        android:layout_height="200dp"
        ws:wsValue="img"/>
```



*******************************************************
