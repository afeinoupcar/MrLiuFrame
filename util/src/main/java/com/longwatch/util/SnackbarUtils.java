package com.longwatch.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by justin on 16/12/3.
 */
public class SnackbarUtils {

    public static void showSnackbar(View view, int resId) {
        showSnackbar(view, view.getResources().getString(resId));
    }

    public static void showSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }
}
