package longwatch.com.mrliuframe.ui.live;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLNetworkManager;

import java.net.UnknownHostException;

import longwatch.com.mrliuframe.BuildConfig;
import longwatch.com.mrliuframe.R;

public class MainPagerActivity extends AppCompatActivity {

    private static final String DEFAULT_TEST_URL = "rtmp://live.hkstv.hk.lxdns.com/live/hks";

    private static final String[] DEFAULT_PLAYBACK_DOMAIN_ARRAY = {
            "live.hkstv.hk.lxdns.com"
    };

    private Spinner mActivitySpinner;
    private EditText mEditText;
    private RadioGroup mStreamingTypeRadioGroup;
    private RadioGroup mDecodeTypeRadioGroup;
private String url;
    public static final String[] TEST_ACTIVITY_ARRAY = {
            "PLMediaPlayerActivity",
            "PLAudioPlayerActivity",
            "PLVideoViewActivity",
            "PLVideoTextureActivity",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpager);
url=getIntent().getStringExtra("videoPath");
        try {
            PLNetworkManager.getInstance().startDnsCacheService(this, DEFAULT_PLAYBACK_DOMAIN_ARRAY);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        TextView mVersionInfoTextView = (TextView) findViewById(R.id.version_info);
        mVersionInfoTextView.setText("Version: " + BuildConfig.VERSION_NAME);

        mEditText = (EditText) findViewById(R.id.VideoPathEdit);
        mEditText.setText(url);

        mStreamingTypeRadioGroup = (RadioGroup) findViewById(R.id.StreamingTypeRadioGroup);
        mDecodeTypeRadioGroup = (RadioGroup) findViewById(R.id.DecodeTypeRadioGroup);

        mActivitySpinner = (Spinner) findViewById(R.id.TestSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TEST_ACTIVITY_ARRAY);
        mActivitySpinner.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PLNetworkManager.getInstance().stopDnsCacheService(this);
    }

    public void onClickLocalFile(View v) {
        Intent intent = new Intent(this, VideoFileActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickPlay(View v) {
        String videopath = mEditText.getText().toString();
        if (!"".equals(videopath)) {
            jumpToPlayerActivity(videopath);
        }
    }

    public void jumpToPlayerActivity(String videopath) {
        Class<?> cls = null;
        switch (mActivitySpinner.getSelectedItemPosition()) {
            case 0:
                cls = PLMediaPlayerActivity.class;
                break;
            case 1:
                cls = PLAudioPlayerActivity.class;
                break;
            case 2:
                cls = PLVideoViewActivity.class;
                break;
            case 3:
                cls = PLVideoTextureActivity.class;
                break;
            default:
                return;
        }
        Intent intent = new Intent(this, cls);
        intent.putExtra("videoPath", videopath);
        if (mDecodeTypeRadioGroup.getCheckedRadioButtonId() == R.id.RadioHWDecode) {
            intent.putExtra("mediaCodec", AVOptions.MEDIA_CODEC_HW_DECODE);
        } else if (mDecodeTypeRadioGroup.getCheckedRadioButtonId() == R.id.RadioSWDecode) {
            intent.putExtra("mediaCodec", AVOptions.MEDIA_CODEC_SW_DECODE);
        } else {
            intent.putExtra("mediaCodec", AVOptions.MEDIA_CODEC_AUTO);
        }
        if (mStreamingTypeRadioGroup.getCheckedRadioButtonId() == R.id.RadioLiveStreaming) {
            intent.putExtra("liveStreaming", 1);
        } else {
            intent.putExtra("liveStreaming", 0);
        }
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        String videoPath = data.getStringExtra("videoPath");
        mEditText.setText(videoPath, TextView.BufferType.EDITABLE);
    }
}
