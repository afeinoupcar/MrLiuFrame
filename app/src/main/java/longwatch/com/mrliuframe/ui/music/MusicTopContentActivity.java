package longwatch.com.mrliuframe.ui.music;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.MusicTopBean;
import longwatch.com.mrliuframe.config.UrlConfig;
import longwatch.com.mrliuframe.ui.ActivityLauncher;
import longwatch.com.mrliuframe.ui.base.ToolbarBaseActivity;
import okhttp3.Call;

public class MusicTopContentActivity extends ToolbarBaseActivity {
    private int topid;
    private String topname;
    private String topnameend = "音乐榜";
    private RecyclerView rv;
    private List<MusicTopBean.ShowapiResBodyEntity.PagebeanEntity.SonglistEntity> data = new ArrayList<>();
    private MusicTopAdapter musicTopAdapter;
    private Toolbar tb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music_top_content;
    }

    @Override
    protected void findViewById() {
        topid = getIntent().getIntExtra("topid", 3);
        topname = getIntent().getStringExtra("topname");
        rv = (RecyclerView) findViewById(R.id.music_top_content_rv);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        tb = toolbar;
        toolbar.setTitle(topname + topnameend);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(null);
    }

    @Override
    protected void initData(Bundle bundle) {
        musicTopAdapter = new MusicTopAdapter(this, data);
        rv.setAdapter(musicTopAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());
        musicTopAdapter.setClickCallbackListener(new MusicTopAdapter.SelectItemClickListener() {
            @Override
            public void onclick(final View itemView, final int position) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityLauncher.viewplayVideo(MusicTopContentActivity.this,data.get(position).getUrl());
                    }
                });
            }
        });
        network();
    }

    private void network() {
        OkHttpUtils.get().url(UrlConfig.MUSIC_TOP_URL + topid).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                MusicTopBean musicTopBean = gson.fromJson(response, MusicTopBean.class);
                if (musicTopBean.getShowapi_res_code() == 0) {
                    data.addAll(musicTopBean.getShowapi_res_body().getPagebean().getSonglist());
                    if(musicTopBean.getShowapi_res_body().getPagebean().getUpdate_time()!=null){
                        tb.setSubtitle("更新时间：" + musicTopBean.getShowapi_res_body().getPagebean().getUpdate_time());
                        tb.setSubtitleTextColor(Color.WHITE);
                    }else{
                        tb.setSubtitle("听最好听的歌，走漫漫人生路！");
                        tb.setSubtitleTextColor(Color.WHITE);
                    }

                    musicTopAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
