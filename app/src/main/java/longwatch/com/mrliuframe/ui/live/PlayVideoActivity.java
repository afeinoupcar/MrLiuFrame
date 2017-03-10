package longwatch.com.mrliuframe.ui.live;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.ui.base.ToolbarBaseActivity;

public class PlayVideoActivity extends ToolbarBaseActivity implements PLMediaPlayer.OnPreparedListener, PLMediaPlayer.OnInfoListener, PLMediaPlayer.OnCompletionListener, PLMediaPlayer.OnVideoSizeChangedListener, PLMediaPlayer.OnErrorListener{

    private PLVideoView mVideoView;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_video;
    }

    @Override
    protected void findViewById() {
        url = getIntent().getStringExtra("url");
        mVideoView = (PLVideoView) findViewById(R.id.PLVideoView);
        MediaController mMediaController=new MediaController(this);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnVideoSizeChangedListener(this);
        mVideoView.setOnErrorListener(this);
        //加载动画
//        View loadingView = findViewById(R.id.LoadingView);
//        mVideoView.setBufferingIndicator(loadingView);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(Bundle bundle) {
        //设置画面预览
//        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_ORIGIN);
//        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
//        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
//        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_16_9);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_4_3);

        mVideoView.setVideoPath(url);
    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {

    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
        return false;
    }

    @Override
    public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(PLMediaPlayer plMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int i, int i1) {

    }
}
