package longwatch.com.mrliuframe.ui.shop;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.longwatch.util.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.ImageBean;
import longwatch.com.mrliuframe.bean.XQBean;
import longwatch.com.mrliuframe.config.UrlConfig;
import longwatch.com.mrliuframe.customview.MyListView;
import longwatch.com.mrliuframe.ui.account.WebViewActivity;
import longwatch.com.mrliuframe.ui.base.ToolbarBaseActivity;
import okhttp3.Call;

public class ShopMsgActivity extends ToolbarBaseActivity implements View.OnClickListener {

    private String xqid;
    private ViewPager vp;
    private XqPagerAdapter xpa;
    private LinearLayout lin;
    private List<ImageView> imgList = new ArrayList<ImageView>();
    private List<XQBean.DataBean.GoodsBean.GalleryBean> data1 = new ArrayList<XQBean.DataBean.GoodsBean.GalleryBean>();
    private TextView shopname;
    private TextView xianjia;
    private TextView yuanjia;
    private TextView yunfei;
    private TextView xiaoliang;
    private TextView shoucangcount;
    private TextView next1;
    private TextView next2;
    private TextView next3;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private MyListView lv;
    private MyListView lv2;
    private MyListView lv3;
    private List<ImageBean> dataimag = new ArrayList<ImageBean>();
    private TextView jiaru;
    private AlertDialog alert;
    private int num = 1;
    private Button jian;
    private Button jia;
    private TextView values;
    private ImageView popimage;
    private TextView popmoney;
    private TextView popkucun;
    private TextView popxiangou;
    private Button popbtn;
    private RadioButton kefu;
    private TextView liji;
    private RadioButton shoucang;
    private XQBean.DataBean db;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_msg;
    }

    @Override
    protected void findViewById() {
        xqid = getIntent().getStringExtra("xqid");
        vp = (ViewPager) findViewById(R.id.xq_vp);
        
        vp.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    vp.requestDisallowInterceptTouchEvent(false);
                } else {
                    vp.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        lin = (LinearLayout) findViewById(R.id.xq_lin);
        shopname = (TextView) findViewById(R.id.xq_shopname);
        xianjia = (TextView) findViewById(R.id.xq_xianjia);
        yuanjia = (TextView) findViewById(R.id.xq_yuanjia);
        yunfei = (TextView) findViewById(R.id.xq_yunfei);
        xiaoliang = (TextView) findViewById(R.id.xq_xiaoliang);
        shoucangcount = (TextView) findViewById(R.id.xq_shoucangcount);
        next1 = (TextView) findViewById(R.id.xq_next1);
        next2 = (TextView) findViewById(R.id.xq_next2);
        next3 = (TextView) findViewById(R.id.xq_next3);
        next1.setOnClickListener(this);
        next2.setOnClickListener(this);
        next3.setOnClickListener(this);
        rb1 = (RadioButton) findViewById(R.id.xq_rb1);
        rb2 = (RadioButton) findViewById(R.id.xq_rb2);
        rb3 = (RadioButton) findViewById(R.id.xq_rb3);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        lv = (MyListView) findViewById(R.id.xq_lv1);
        lv.setFocusable(false);
        lv2 = (MyListView) findViewById(R.id.xq_lv2);
        lv2.setFocusable(false);
        lv3 = (MyListView) findViewById(R.id.xq_lv3);
        lv3.setFocusable(false);
        jiaru = (TextView) findViewById(R.id.xq_jiaru);
        jiaru.setOnClickListener(this);
        kefu = (RadioButton) findViewById(R.id.xq_kefu);
        kefu.setOnClickListener(this);
        liji = (TextView) findViewById(R.id.xq_liji);
        liji.setOnClickListener(this);
        shoucang = (RadioButton) findViewById(R.id.xq_shoucang);
        shoucang.setOnClickListener(this);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("商品详情");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(null);
    }

    @Override
    protected void initData(Bundle bundle) {
        OkHttpUtils.get().url(UrlConfig.SHOPMSG_URL+xqid).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                XQBean bean = gson.fromJson(response, XQBean.class);
                XQBean.DataBean dataBean = bean.getData();
                data1.clear();
                data1.addAll(dataBean.getGoods().getGallery());
                db = bean.getData();
                getJson(dataBean.getGoods().getGoods_desc());
                setdot();
                setPageChangeListener();
                // TODO Auto-generated method stub
                xpa = new XqPagerAdapter(dataBean.getGoods().getGallery(), ShopMsgActivity.this);
                vp.setAdapter(xpa);
                shopname.setText(dataBean.getGoods().getGoods_name());
                xianjia.setText("现价 ￥：" + dataBean.getGoods().getShop_price());
                yuanjia.setText("原价 ￥：" + dataBean.getGoods().getMarket_price());
                yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                xiaoliang.setText("销量:" + dataBean.getGoods().getSales_volume());
                shoucangcount.setText("收藏:" + dataBean.getGoods().getCollect_count());
                next1.setText(dataBean.getActivity().get(0).getTitle());
                next2.setText(dataBean.getActivity().get(1).getTitle());
                next3.setText(dataBean.getActivity().get(2).getTitle());
                rb3.setText("评论（" + dataBean.getCommentNumber() + "）");
                OneListAdapter oa = new OneListAdapter(dataBean.getGoods().getAttributes(),
                        ShopMsgActivity.this);
                lv.setAdapter(oa);
                if (dataBean.getComments() != null) {
                    TwoListAdapter ta = new TwoListAdapter(dataBean.getComments(), ShopMsgActivity.this);
                    lv2.setAdapter(ta);
                }

                ThreeListAdapter tla = new ThreeListAdapter(dataimag, ShopMsgActivity.this);
                lv3.setAdapter(tla);
                xpa.notifyDataSetChanged();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           finish();
            return true;
        }else if(item.getItemId() ==R.id.action_edit){
            ToastUtils.showToast(this,"go find");
            return true;
        }else if(item.getItemId() ==R.id.action_share){
            new ShareAction(ShopMsgActivity.this)
                    .withText("hello")
                    .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                    .setCallback(umShareListener)
                    .open();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(ShopMsgActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShopMsgActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShopMsgActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    protected void getJson(String goods_desc) {
        try {
            List<ImageBean> li = new ArrayList<ImageBean>();
            JSONArray array = new JSONArray(goods_desc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = (JSONObject) array.get(i);
                ImageBean bean = new ImageBean();
                bean.setUrl(o.optString("url"));
                li.add(bean);
            }
            dataimag.clear();
            dataimag.addAll(li);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void setdot() {

        if (imgList != null) {
            imgList.clear();
        }

        for (int i = 0; i < data1.size(); i++) {
            ImageView img_dot = new ImageView(ShopMsgActivity.this);
            if (i == 0) {
                img_dot.setImageResource(R.drawable.page_shape);
            } else {
                img_dot.setImageResource(R.drawable.page_shape_no);

            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(10, 0, 10, 0);

            imgList.add(img_dot);
            lin.addView(img_dot, params);
        }

    }

    /**
     * 设置viewpager的滑动监听事件
     */
    private void setPageChangeListener() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			/*
			 * 此方法为滑动结束时(non-Javadoc)
			 *
			 * @see android.support.v4.view.ViewPager.OnPageChangeListener#
			 * onPageSelected (int)
			 */

            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < imgList.size(); i++) {
                    if (arg0 % data1.size() == i) {
                        imgList.get(arg0 % data1.size()).setImageResource(R.drawable.page_shape);
                    } else {
                        imgList.get(i).setImageResource(R.drawable.page_shape_no);
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.xq_next1:
                Intent intent = new Intent(ShopMsgActivity.this, WebViewActivity.class);
                intent.putExtra("url", db.getActivity().get(0).getDescription());
                startActivity(intent);
                break;
            case R.id.xq_next2:
                Intent intent2 = new Intent(ShopMsgActivity.this, WebViewActivity.class);
                intent2.putExtra("url", db.getActivity().get(1).getDescription());
                startActivity(intent2);
                break;
            case R.id.xq_next3:
                Intent intent3 = new Intent(ShopMsgActivity.this, WebViewActivity.class);
                intent3.putExtra("url", db.getActivity().get(2).getDescription());
                startActivity(intent3);
                break;
            case R.id.xq_rb1:
                lv.setVisibility(View.GONE);
                lv2.setVisibility(View.GONE);
                lv3.setVisibility(View.VISIBLE);
                rb1.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                rb2.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                rb3.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
            case R.id.xq_rb2:
                lv.setVisibility(View.VISIBLE);
                lv2.setVisibility(View.GONE);
                lv3.setVisibility(View.GONE);
                rb2.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                rb1.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                rb3.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
            case R.id.xq_rb3:
                lv.setVisibility(View.GONE);
                lv2.setVisibility(View.VISIBLE);
                lv3.setVisibility(View.GONE);
                rb3.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                rb1.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                rb2.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
            case R.id.xq_jiaru:
                inCar();
                break;
            case R.id.xq_kefu:
                Toast toast = Toast.makeText(ShopMsgActivity.this, "亲，有什么可以帮您的吗？", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case R.id.xq_liji:
                lijigoumai();
                break;
            case R.id.xq_shoucang:
                

                break;
            default:
                break;
        }
    }

    private void lijigoumai() {
    }

    private void inCar() {
    }
}
