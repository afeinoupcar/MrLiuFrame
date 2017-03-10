package longwatch.com.mrliuframe.ui.live;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.longwatch.util.ui.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.RmBean;
import longwatch.com.mrliuframe.config.UrlConfig;
import longwatch.com.mrliuframe.customview.MyRecyclerView;
import longwatch.com.mrliuframe.ui.ActivityLauncher;
import okhttp3.Call;

/**
 * Created by a on 2017/2/9.
 */

public class LiveFragment extends BaseFragment{
    private ViewPager vp;
    private MyRecyclerView rv;
    private LinearLayout lin;
    private List<RmBean.ContentBean.BannerBean> data1 = new ArrayList<RmBean.ContentBean.BannerBean>();
    private List<RmBean.ContentBean.ListBean> data2 = new ArrayList<RmBean.ContentBean.ListBean>();
    private List<ImageView> imgList = new ArrayList<ImageView>();
    private RmVpAdapter rmVpAdapter;
    private LiveRvAdapter liveRvAdapter;
    private int code=0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int a = msg.what;
            switch (a) {
                case 0:
                    setdot();
                    vp.setCurrentItem(1000000);
                    rmVpAdapter = new RmVpAdapter(data1, getActivity(),handler);
                    vp.setAdapter(rmVpAdapter);

    liveRvAdapter =new LiveRvAdapter(getActivity(),data2);
    rv.setAdapter(liveRvAdapter);
    liveRvAdapter.setClickCallbackListener(new LiveRvAdapter.SelectItemClickListener() {
        @Override
        public void onclick(View itemView, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityLauncher.viewplayVideo(getActivity(),data2.get(position).getVideo());
                }
            });
        }
    });
                    sendEmptyMessage(1);
                    setPageChangeListener();
                    break;
                case 1:
                    int count = vp.getCurrentItem();
                    count++;
                    vp.setCurrentItem(count);
                    vp.setPageTransformer(true, new ViewPager.PageTransformer() {
                        @Override
                        public void transformPage(View view, float position) {
                            float MIN_ALPHA = 0.25f;
                            if (position < -1) { // [-Infinity,-1)
                                view.setAlpha(0);
                            } else if (position <= 1) { // [-1,1]
                                float alpha = (1 - Math.abs(position)) * (1 - MIN_ALPHA) + MIN_ALPHA;
                                view.setAlpha(alpha);
                            } else { // (1,+Infinity]
                                view.setAlpha(0);
                            }
                        }
                    });
                    handler.sendEmptyMessageDelayed(1, 2000);
                    break;

                default:
                    rmVpAdapter.notifyDataSetChanged();
                    liveRvAdapter.notifyDataSetChanged();
                    break;
            }
        };
    };
    private MaterialRefreshLayout refreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initView(View parent) {
        vp=(ViewPager)parent.findViewById(R.id.live_fra_vp);
        rv=(MyRecyclerView)parent.findViewById(R.id.live_fra_rv);
        lin = (LinearLayout) parent.findViewById(R.id.live_fra_lin);
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
        rv.setFocusable(false);
        refreshLayout=(MaterialRefreshLayout)parent.findViewById(R.id.live_fra_mrl);
    }

    @Override
    protected void initData(Bundle bundle) {
        LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
network();
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        network();
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);


            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        network();
                        materialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 3000);

            }
        });
    }

    private void network() {
        OkHttpUtils.get().url(UrlConfig.LIVE_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                RmBean bean = gson.fromJson(response, RmBean.class);
                data1.clear();
                data1.addAll(bean.getContent().getBanner());
                data2.clear();
                data2.addAll(bean.getContent().getList());
                handler.sendEmptyMessage(code);
                code=2;
            }
        });
    }

    private void setdot() {
        // sy_lin.removeAllViews();
        for (int i = 0; i < data1.size(); i++) {
            ImageView img_dot = new ImageView(getActivity());
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
}
