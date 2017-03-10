package longwatch.com.mrliuframe.ui.news;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.longwatch.util.ui.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.NewsBean;
import longwatch.com.mrliuframe.config.UrlConfig;
import longwatch.com.mrliuframe.ui.ActivityLauncher;
import okhttp3.Call;

/**
 * Created by a on 2017/2/14.
 */

public class NewsContentFragment extends BaseFragment{
    private int size;
    private RecyclerView recyclerView;
    private List<NewsBean.DataEntity> data = new ArrayList<>();
    private String[] arr={UrlConfig.SHEHUI_URL,UrlConfig.REDIAN_URL,UrlConfig.BENDI_URL,UrlConfig.SHIPIN_URL,UrlConfig.SHEHUI_URL,UrlConfig.YULE_URL,UrlConfig.KEJI_URL,UrlConfig.QICHE_URL,UrlConfig.TIYU_URL,UrlConfig.CAIJING_URL,UrlConfig.JUNSHI_URL};
    private NewsAdapter newsAdapter;
private MaterialRefreshLayout refreshLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_content;
    }

    @Override
    protected void initView(View parent) {
    size=getArguments().getInt("page");
        recyclerView=(RecyclerView)parent.findViewById(R.id.newscontent_fra_rv);
        refreshLayout=(MaterialRefreshLayout)parent.findViewById(R.id.newscontent_fra_mrl);
    }

    @Override
    protected void initData(Bundle bundle) {
        newsAdapter=new NewsAdapter(data,getActivity());
        recyclerView.setAdapter(newsAdapter);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        newsAdapter.setClickCallbackListener(new NewsAdapter.SelectItemClickListener() {
            @Override
            public void onclick(View itemView, final int position) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityLauncher.viewWebView(getActivity(),data.get(position).getUrl());
                    }
                });
            }
        });
switch (size){
    case 0:
        netData(arr[size]);
        break;
    case 1:
        netData(arr[size]);
        break;
    case 2:
        netData(arr[size]);
        break;
    case 3:
        netData(arr[size]);
        break;
    case 4:
        netData(arr[size]);
        break;
    case 5:
        netData(arr[size]);
        break;
    case 6:
        netData(arr[size]);
        break;
    case 7:
        netData(arr[size]);
        break;
    case 8:
        netData(arr[size]);
        break;
    case 9:
        netData(arr[size]);
        break;
    case 10:
        netData(arr[size]);
        break;
}
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        netData(arr[size]);
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);


            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        netData(arr[size]);
                        materialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 3000);

            }
        });
    }

    private void netData(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(), "当前没有网络！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                if(response!=null){
                    Gson gson=new Gson();
                    NewsBean newsBean= gson.fromJson(response,NewsBean.class);
                    List<NewsBean.DataEntity> list=newsBean.getData();
                    data.clear();
                    data.addAll(list);
                    newsAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
