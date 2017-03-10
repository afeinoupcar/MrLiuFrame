package longwatch.com.mrliuframe.ui.shop;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.longwatch.util.ui.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.ShopBean;
import longwatch.com.mrliuframe.config.UrlConfig;
import longwatch.com.mrliuframe.ui.ActivityLauncher;
import okhttp3.Call;

/**
 * Created by a on 2017/2/9.
 */

public class ShopFragment extends BaseFragment {
    private RecyclerView rv;
    private List<ShopBean.DataEntity> data = new ArrayList<>();
    private ShopRvAdapter shopRvAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView(View parent) {
        rv = (RecyclerView) parent.findViewById(R.id.shop_fra_rv);
    }

    @Override
    protected void initData(Bundle bundle) {
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rv.setItemAnimator(new DefaultItemAnimator());
        shopRvAdapter=new ShopRvAdapter(getActivity(),data);
        rv.setAdapter(shopRvAdapter);
        shopRvAdapter.setClickCallbackListener(new ShopRvAdapter.SelectItemClickListener() {
            @Override
            public void onclick(View itemView, final int position) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityLauncher.viewShopMsg(getActivity(),data.get(position).getId());
                    }
                });
            }
        });
        netWork();
    }

    private void netWork() {
        OkHttpUtils.get().url(UrlConfig.SHOP_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                ShopBean shopBean =gson.fromJson(response, ShopBean.class);
                data.clear();
                data.addAll(shopBean.getData());
                shopRvAdapter.notifyDataSetChanged();
            }
        });
    }
}
