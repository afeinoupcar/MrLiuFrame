package longwatch.com.mrliuframe;

import com.longwatch.util.BaseApplication;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by a on 2017/2/8.
 */

public class MyApplication extends BaseApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG=true;
        PlatformConfig.setWeixin("wx39d17b048caecd8f", "0538ee1604eefa19aeec0e666318104d");
        PlatformConfig.setQQZone("1105981674", "IzpAsDWV3866vmRb");
        UMShareAPI.get(this);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
