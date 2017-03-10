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
//显示进度
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
	//如果不做任何处理，浏览网页，点击系统“Back”键，整个Browser会调用finish()而结束自身
	//，如果希望浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件。
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
			wv.goBack();//退回到上一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
