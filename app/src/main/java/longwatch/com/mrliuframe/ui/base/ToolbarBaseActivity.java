package longwatch.com.mrliuframe.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.longwatch.util.SnackbarUtils;
import com.longwatch.util.network.RequestError;

import longwatch.com.mrliuframe.R;


public abstract class ToolbarBaseActivity extends NavigationBarBaseActivity {

    protected Toolbar mToolbar;
    protected FrameLayout mContentLayout;
    protected CoordinatorLayout mCoordinatorLayout;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StateBarUtil.setTransparentStateBar(this);
        setContentView(getBaseLayoutId());
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            // setContentView(layoutId);
            mContentLayout.addView(LayoutInflater.from(this).inflate(layoutId, null));
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.layout_base);
        findViewById();
        if (savedInstanceState != null) {
            initData(savedInstanceState);
        } else {
            initData(getIntent().getExtras());
        }
        initToolbar(mToolbar);//Toolbar的setTitle方法要在setSupportActionBar(toolbar)之前调用，否则不起作用
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected int getBaseLayoutId() {
        return R.layout.activity_layout_base;
    }

    protected abstract int getLayoutId();

    protected abstract void findViewById();

    protected abstract void initToolbar(Toolbar toolbar);

    protected abstract void initData(Bundle bundle);


    protected void hideToolBarLayout(boolean hide) {
        int visibility = hide ? View.GONE : View.VISIBLE;
        if (mToolbar != null) {
            mToolbar.setVisibility(visibility);
        }
    }

    protected void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showSnackbar(int resId) {
        showSnackbar(getString(resId));
    }

    public void showSnackbar(String text) {
        SnackbarUtils.showSnackbar(mContentLayout, text);
    }

    public void errorCode(int code) {
        switch (code) {
            case RequestError.CODE_NO_NETWORK:
                showSnackbar(R.string.no_network_message);
                break;
            case RequestError.CODE_ERROR:
                showSnackbar(R.string.error_network_message);
                break;
            case RequestError.CODE_ERROR_200:
                showSnackbar(R.string.error_200_network_message);
                break;
        }
    }
}
