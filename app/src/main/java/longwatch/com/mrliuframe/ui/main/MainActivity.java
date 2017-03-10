package longwatch.com.mrliuframe.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.longwatch.util.SnackbarUtils;
import com.longwatch.util.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;
import java.util.Set;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.ui.base.ToolbarBaseActivity;
import longwatch.com.mrliuframe.ui.live.LiveFragment;
import longwatch.com.mrliuframe.ui.music.MusicFragment;
import longwatch.com.mrliuframe.ui.news.NewsFragment;
import longwatch.com.mrliuframe.ui.shop.ShopFragment;

public class MainActivity extends ToolbarBaseActivity implements View.OnClickListener {
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private UMShareAPI mShareAPI;
    /**
     * 两次返回退出应用间隔时间
     */
    private static final long DIFF_DEFAULT_BACK_TIME = 2000;
    /**
     * 上次返回时间
     */
    private long mBackTime = -1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViewById() {
        rg = (RadioGroup) findViewById(R.id.main_ac_rg);
        rb1 = (RadioButton) findViewById(R.id.main_ac_rb1);
        rb2 = (RadioButton) findViewById(R.id.main_ac_rb2);
        rb3 = (RadioButton) findViewById(R.id.main_ac_rb3);
        rb4 = (RadioButton) findViewById(R.id.main_ac_rb4);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.activity_main);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
        mShareAPI = UMShareAPI.get(this);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        mToolbar=toolbar;
        mToolbar.setTitle("前沿资讯");
        mToolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected void initData(Bundle bundle) {
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }else if(item.getItemId() ==R.id.action_edit){
            ToastUtils.showToast(this,"go find");
            return true;
        }else if(item.getItemId() ==R.id.action_share){
            new ShareAction(MainActivity.this)
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
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        NewsFragment mNewsFragmnet= (NewsFragment) fragmentManager.findFragmentByTag("fragmentNews");
        LiveFragment mLiveFragmnet= (LiveFragment) fragmentManager.findFragmentByTag("fragmentLive");
        ShopFragment mShopFragmnet=(ShopFragment)fragmentManager.findFragmentByTag("fragmentShop");
        MusicFragment mMusicFragmnet=(MusicFragment)fragmentManager.findFragmentByTag("fragmentMusic");
        switch (v.getId()){
            case R.id.main_ac_rb1:
                mToolbar.setTitle("前沿资讯");
                FragmentTransaction transaction1=fragmentManager.beginTransaction();
                transaction1.show(mNewsFragmnet);
                transaction1.hide(mLiveFragmnet);
                transaction1.hide(mShopFragmnet);
                transaction1.hide(mMusicFragmnet);
                transaction1.commit();
                break;
            case R.id.main_ac_rb2:
                mToolbar.setTitle("娱乐互动");
                FragmentTransaction transaction2=fragmentManager.beginTransaction();
                transaction2.hide(mNewsFragmnet);
                transaction2.show(mLiveFragmnet);
                transaction2.hide(mShopFragmnet);
                transaction2.hide(mMusicFragmnet);
                transaction2.commit();
                break;
            case R.id.main_ac_rb3:
                mToolbar.setTitle("时尚名牌");
                FragmentTransaction transaction3=fragmentManager.beginTransaction();
                transaction3.hide(mNewsFragmnet);
                transaction3.hide(mLiveFragmnet);
                transaction3.show(mShopFragmnet);
                transaction3.hide(mMusicFragmnet);
                transaction3.commit();
                break;
            case R.id.main_ac_rb4:
                mToolbar.setTitle("动感地带");
                FragmentTransaction transaction4=fragmentManager.beginTransaction();
                transaction4.hide(mNewsFragmnet);
                transaction4.hide(mLiveFragmnet);
                transaction4.hide(mShopFragmnet);
                transaction4.show(mMusicFragmnet);
                transaction4.commit();
                break;
        }
    }
    public void qqlogin(View view){
            mShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
    }
    public void wxlogin(View view){
        mShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
    }
    public void phlogin(View view){
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
    private UMAuthListener umAuthListener=new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
           Set<String> set=map.keySet();
            for (String string : set) {
                String str = map.get(string);
                // 设置头像
                if (string.equals("iconurl")) {
                    System.out.println(str);
                }
                // 设置昵称
                if (string.equals("name")) {
                    System.out.println(str);
                }

                if (string.equals("gender")) {
                    System.out.println(str);
                }

            }

        }
        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SnackbarUtils.showSnackbar(mDrawerLayout,"请先安装第三方应用！");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
    @Override
    public void onBackPressed() {
        // 连续点击返回退出
        long nowTime = SystemClock.elapsedRealtime();
        long diff = nowTime - mBackTime;
        if (diff >= DIFF_DEFAULT_BACK_TIME) {
            mBackTime = nowTime;
//                ToastUtils.showToast(this, R.string.toast_exit_app_str);
            showSnackbar(R.string.toast_exit_app_str);
        } else {
            super.onBackPressed();
        }
    }
}
