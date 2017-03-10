package longwatch.com.mrliuframe.customview.dialog;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.config.Constant;


/**
 * Created by justin on 16/12/4.
 */
public class SelectGenderDialog extends AnimationDialog implements View.OnClickListener {

    private SelectGenderClickListener mCallbackListener;
    private View layout_gender_man;
    private View layout_gender_girl;
    private View layout_gender_secret;

    public SelectGenderDialog(Activity context) {
        super(context);
    }

    @Override
    protected View obtainView(Context context) {
        View parent = LayoutInflater.from(context).inflate(R.layout.dialog_layout_select_gender, null);
        layout_gender_man = parent.findViewById(R.id.layout_gender_man);
        layout_gender_man.setOnClickListener(this);
        layout_gender_girl = parent.findViewById(R.id.layout_gender_girl);
        layout_gender_girl.setOnClickListener(this);
        layout_gender_secret = parent.findViewById(R.id.layout_gender_secret);
        layout_gender_secret.setOnClickListener(this);
        parent.findViewById(R.id.cancel).setOnClickListener(this);
        return parent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_gender_man:
                selectMan();
                break;
            case R.id.layout_gender_girl:
                selectGirl();
                break;
            case R.id.layout_gender_secret:
                selectSecret();
                break;
            case R.id.cancel:
                cancel();
                break;
            default:
                break;
        }
    }

    protected void selectMan() {
        if (mCallbackListener != null) {
            mCallbackListener.selectMan(this);
        }
    }

    protected void selectGirl() {
        if (mCallbackListener != null) {
            mCallbackListener.selectGirl(this);
        }
    }

    protected void selectSecret() {
        if (mCallbackListener != null) {
            mCallbackListener.selectSecret(this);
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

    public void setClickCallbackListener(SelectGenderClickListener listener) {
        mCallbackListener = listener;
    }

    public void setSelected(String gender) {
        if (Constant.GENDER_MAN.equals(gender)) {
            layout_gender_man.setSelected(true);
            layout_gender_girl.setSelected(false);
        } else if (Constant.GENDER_GIRL.equals(gender)) {
            layout_gender_man.setSelected(false);
            layout_gender_girl.setSelected(true);
        } else {
            layout_gender_man.setSelected(false);
            layout_gender_girl.setSelected(false);
        }
    }

    public interface SelectGenderClickListener {

        void selectMan(SelectGenderDialog dialog);

        void selectGirl(SelectGenderDialog dialog);

        void selectSecret(SelectGenderDialog dialog);
    }
}