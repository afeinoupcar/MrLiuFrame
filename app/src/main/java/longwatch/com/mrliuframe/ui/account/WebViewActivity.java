package longwatch.com.mrliuframe.ui.account;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.ui.base.ToolbarBaseActivity;

public class WebViewActivity extends ToolbarBaseActivity {

	private WebView wv;
	private String url;
	private ProgressBar progressBar;
	@Override
	protected int getLayoutId() {
		return R.layout.activity_webview;
	}

	@Override
	protected void findViewById() {
		progressBar=(ProgressBar)findViewById(R.id.webView_progressBar);
	}

	@Override
	protected void initToolbar(Toolbar toolbar) {
hideToolBarLayout(true);
	}

	@Override
	protected void initData(Bundle bundle) {
		getContent();
	}

	private void getContent() {
		url = getIntent().getStringExtra("url");
		wv = (WebView) findViewById(R.id.content_f_wv);
		wv.loadUrl(url);
		wv.setWebViewClient(new WebViewClient());
//��ʾ����
		wv.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				progressBar.setProgress(newProgress);
				if(newProgress >= 100){
					progressBar.setVisibility(View.GONE);
				}
//                super.onProgressChanged(view, newProgress);
			}

		});
				WebSettings settings = wv.getSettings();

				settings.setSupportZoom(true);

				settings.setBuiltInZoomControls(true);

				settings.setDefaultZoom(ZoomDensity.CLOSE);

				settings.setJavaScriptEnabled(true);

				settings.setPluginState(PluginState.ON);
				settings.setDomStorageEnabled(true);
	}
	//��������κδ��������ҳ�����ϵͳ��Back����������Browser�����finish()����������
	//�����ϣ��������� ҳ���˶������Ƴ����������Ҫ�ڵ�ǰActivity�д������ѵ���Back�¼���
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
			wv.goBack();//�˻ص���һ��ҳ��
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
