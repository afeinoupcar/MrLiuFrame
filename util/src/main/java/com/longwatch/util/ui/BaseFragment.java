package com.longwatch.util.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.longwatch.util.R;
import com.longwatch.util.SnackbarUtils;
import com.longwatch.util.network.RequestError;

import java.lang.reflect.Method;

/**
 * Created by justin on 16/12/3.
 */
public abstract class BaseFragment extends Fragment {

//    private View mContentView;
    private int count;
    private long firstClick, secondClick;
    private int viewId1, viewId2;

    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getActivity() != null && getActivity().isFinishing()) {
            return null;
        }
//        if(mContentView == null) {
//            mContentView = (ViewGroup) inflater.inflate(getLayoutId(), container, false);
//            initView(mContentView);
//        }
        ViewGroup rootView = (ViewGroup) inflater.inflate(getLayoutId(), container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null && getActivity().isFinishing()) {
            return;
        }
        if (savedInstanceState != null) {
            initData(savedInstanceState);
        } else {
            if (getActivity() != null && getActivity().getIntent() != null) {
                initData(getActivity().getIntent().getExtras());
            } else {
                initData(null);
            }
        }
    }

    protected abstract int getLayoutId();
    protected abstract void initView(View parent);
    protected abstract void initData(Bundle bundle);
    protected void onDoubleClick(){}

    /**
     * 利用反射让隐藏在Overflow中的MenuItem显示Icon图标
     * @param menu
     * onMenuOpened方法中调用
     */
    public static void setOverflowIconVisible(Menu menu) {
        if (menu != null && menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected View.OnTouchListener onDoubleClick = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(MotionEvent.ACTION_DOWN == event.getAction()){
                count++;
                if (count == 1) {
                    firstClick = System.currentTimeMillis();
                    viewId1 = v.getId();
                } else if (count == 2) {
                    secondClick = System.currentTimeMillis();
                    viewId2 = v.getId();
                    if (secondClick - firstClick < 1000) {
                        if (viewId1 == viewId2) {
                            onDoubleClick();
                        }
                    }
                    count = 0;
                    firstClick = 0;
                    secondClick = 0;
                }
            }
            return false;
        }
    };

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    protected boolean canUpdateUI() {
        return isOnMainThread() && getActivity() != null;//isAdded();
    }

    protected void showSnackbar(int resId) {
        showSnackbar(getString(resId));
    }

    protected void showSnackbar(String text) {
        SnackbarUtils.showSnackbar(getView(), text);
    }

    @Override
    public void onDestroy() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        super.onDestroy();
    }

    protected final void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected final void hideLoading() {
        if (getActivity() != null && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void errorCode(int code) {
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
