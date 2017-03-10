package longwatch.com.mrliuframe.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.longwatch.util.ToastUtils;

import java.io.File;

import longwatch.com.mrliuframe.MyApplication;
import longwatch.com.mrliuframe.R;

/**
 * Created by justin on 16/12/5.
 */
public class TakePicUtil {

    public static String mPhotoFilePath;
    public static final int REQUEST_CODE_TAKE_PICTURE = 100;
    public static final int REQUEST_CODE_PICK_PICTURE = REQUEST_CODE_TAKE_PICTURE + 1;

    public static void fromCamera(Activity ctt, Fragment frag, boolean useFrag){
        File cacheDir = MyApplication.getContext().getExternalCacheDir();
        if (cacheDir != null) {
            String pictureName = System.currentTimeMillis()+".jpeg";
            mPhotoFilePath = new StringBuilder(cacheDir.getAbsolutePath()).append(File.separator).append(pictureName)
                    .toString();
        }
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(mPhotoFilePath);
        if (file.exists()) {
            file.delete();
        }
        Uri uri = Uri.fromFile(file);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        takePhotoIntent.putExtra("orientation", 0);
        try {
            if (useFrag) {
                frag.startActivityForResult(takePhotoIntent, REQUEST_CODE_TAKE_PICTURE);
            } else {
                ctt.startActivityForResult(takePhotoIntent, REQUEST_CODE_TAKE_PICTURE);
            }

        } catch (ActivityNotFoundException e) {
            ToastUtils.showToast(ctt, R.string.toast_camera_error);
        }
    }
}
