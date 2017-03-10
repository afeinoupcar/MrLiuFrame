package longwatch.com.mrliuframe;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import longwatch.com.mrliuframe.ui.ActivityLauncher;
import longwatch.com.mrliuframe.ui.base.NavigationBarBaseActivity;
import longwatch.com.mrliuframe.util.StateBarUtil;

/**
 * Created by justin on 16/12/4.
 */
public class SplashActivity extends NavigationBarBaseActivity {
private RelativeLayout mSplashLayout;
private TextView mTextView;
    private Timer mTimer;
    private int mSize=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StateBarUtil.setInVisibilityStateBar(this);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }
    private void initView() {
        mSplashLayout=(RelativeLayout)findViewById(R.id.splash_layout);
        mTextView=(TextView)findViewById(R.id.splash_ac_time);
    }
    private void initData() {
      Animation mAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_fade_in_and_zoom_in);
        mSplashLayout.startAnimation(mAnimation);
        if(mTimer==null){
            mTimer=new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            mSize--;
                if(mSize==3){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("time:three");
                        }
                    });
                }else if(mSize==2){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("time:two");
                        }
                    });
                }else if(mSize==1){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("time:one");
                        }
                    });
                }else if(mSize==0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("time:zero");
                        }
                    });
                    ActivityLauncher.viewMain(SplashActivity.this);
                    finish();
                }

            }
        },1500,1000);
    }

    @Override
    protected void onDestroy() {
        mSize=3;
        mTimer.cancel();
        mTimer=null;
        super.onDestroy();
    }
}

