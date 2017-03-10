package longwatch.com.mrliuframe.customview.dialog;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import longwatch.com.mrliuframe.R;


/**
 * Created by justin on 16/12/4.
 * 图片／拍照对话框
 */
public class SelectPicDialog extends AnimationDialog implements View.OnClickListener {

    private SelectPicClickListener mCallbackListener;

    public SelectPicDialog(Activity context) {
        super(context);
    }

    @Override
    protected View obtainView(Context context) {
        View parent = LayoutInflater.from(context).inflate(R.layout.dialog_layout_select_pic, null);
        parent.findViewById(R.id.layout_camera).setOnClickListener(this);
        parent.findViewById(R.id.layout_pic).setOnClickListener(this);
        parent.findViewById(R.id.cancel).setOnClickListener(this);
        return parent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_camera :
                fromCamera();
                break;
            case R.id.layout_pic :
                fromPic();
                break;
            case R.id.cancel :
                cancel();
                break;
            default :
                break;
        }
    }

    protected void fromCamera() {
        if (mCallbackListener != null) {
            mCallbackListener.fromCamera(this);
        }
    }

    protected void fromPic() {
        if (mCallbackListener != null) {
            mCallbackListener.fromPic(this);
        }
    }

    public void cancel() {
        dismiss();
    }

    @Override
    public void destroy() {
        mCallbackListener = null;
        super.destroy();
    }

    public void setClickCallbackListener(SelectPicClickListener listener) {
        mCallbackListener = listener;
    }
    public interface SelectPicClickListener {

        void fromCamera(SelectPicDialog dialog);

        void fromPic(SelectPicDialog dialog);
    }
}