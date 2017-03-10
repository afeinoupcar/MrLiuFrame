package longwatch.com.mrliuframe.config;

/**
 * Created by a on 2016/12/14.
 */
//基本长量类
public class Constant {
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();

    public static final int MAX_DISK_CACHE_SIZE = 40 * 1048576; // 40MB
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
    public static final String PLATFORM = "1";
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_MOBILE = 2;

    public static final String GENDER_MAN = "男";
    public static final String GENDER_GIRL = "女";
    public static final String GENDER_SECRET = "保密";

    public static class SPKey {
        public static final String SP_KEY_SHOW_GUIDE_LAYOUT = "sp_key_show_guide_layout";

        public static final String APP_PLATFORM_NAME = "app_platformName";
        public static final String APP_URL_IP = "app_url_ip";
        public static final String APP_URL_PORT = "app_url_port";

        /** apk使用系统DownloadManager时的id */
        public static final String APK_DOWNLOAD_ID = "apk_download_id";

        //user info
        public static final String SP_KEY_USER_CHECK_CODE = "sp_key_user_checkCode";
        public static final String SP_KEY_USER_COMPANY_CODE = "sp_key_user_companyCode";
        public static final String SP_KEY_USER_CREATE_TIME = "sp_key_user_createTime";
        public static final String SP_KEY_USER_CUSTOMER_CODE = "sp_key_user_customerCode";
        public static final String SP_KEY_USER_ID = "sp_key_user_id";
        public static final String SP_KEY_USER_LOGIN_NAME = "sp_key_user_loginName";
        public static final String SP_KEY_USER_LOGIN_PASS = "sp_key_user_loginPass";
        public static final String SP_KEY_USER_MAIL = "sp_key_user_mail";
        public static final String SP_KEY_USER_METER_ALLOCATION = "sp_key_user_meterAllocation";
        public static final String SP_KEY_USER_MOBILE_PHONE = "sp_key_user_mobilePhone";
        public static final String SP_KEY_USER_NEW_PASS = "sp_key_user_newPass";
        public static final String SP_KEY_USER_REAL_NAME = "sp_key_user_realName";
        public static final String SP_KEY_USER_ROLE_CODE = "sp_key_user_roleCode";
        public static final String SP_KEY_USER_STATUS = "sp_key_user_status";
        public static final String SP_KEY_USER_TELEPHONE = "sp_key_user_telePhone";
        public static final String SP_KEY_USER_UPDATE_TIME = "sp_key_user_updateTime";
        public static final String SP_KEY_USER_USER_TYPE = "sp_key_user_userType";

    }
}
