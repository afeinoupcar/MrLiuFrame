package longwatch.com.mrliuframe.ui;

import android.content.Context;
import android.content.Intent;

import longwatch.com.mrliuframe.ui.account.WebViewActivity;
import longwatch.com.mrliuframe.ui.live.MainPagerActivity;
import longwatch.com.mrliuframe.ui.main.MainActivity;
import longwatch.com.mrliuframe.ui.music.MusicTopContentActivity;
import longwatch.com.mrliuframe.ui.shop.ShopMsgActivity;

/**
 * Created by a on 2017/2/7.
 */

public class ActivityLauncher {
    /**
     * 跳转到主界面
     *
     * @param context
     */
    public static void viewMain(Context context) {
        viewMain(context, false);
    }

    /**
     * 跳转到主界面
     *
     * @param context
     * @param isClearTask 是否清除栈信息
     */
    public static void viewMain(Context context, boolean isClearTask) {
        Intent intent = new Intent(context, MainActivity.class);
        if (isClearTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);
    }
    public static void viewWebView(Context context,String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    public static void viewplayVideo(Context context,String url) {
        Intent intent = new Intent(context, MainPagerActivity.class);
        intent.putExtra("videoPath",url);
        context.startActivity(intent);
    }
    public static void viewShopMsg(Context context,String id) {
        Intent intent = new Intent(context, ShopMsgActivity.class);
        intent.putExtra("xqid", id);
        context.startActivity(intent);
    }
    public static void viewMusicTopContent(Context context,int topid,String topname) {
        Intent intent = new Intent(context, MusicTopContentActivity.class);
        intent.putExtra("topid", topid);
        intent.putExtra("topname",topname);
        context.startActivity(intent);
    }
}
