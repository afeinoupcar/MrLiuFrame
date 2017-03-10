package longwatch.com.mrliuframe.customview.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import longwatch.com.mrliuframe.R;


/**
 * 渐入检出动画对话框
 * 
 * public MyActivity extent Activity {
 *    private AnimationDialog mAnimationDialog;
 *    @Override
 *    protected void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         initAnimationDialog();
 *    }
 *    
 *    private void initAnimationDialog() {
 *        if (mAnimationDialog == null) {
 *            mAnimationDialog = new AnimationDialog(this) {
 *                @Override
 *                protected View obtainView(Context context) {
 *                  // TODO Auto-generated method stub
 *                  return null;
 *                }
 *            };
 *        }
 *        mAnimationDialog.show();
 *    }
 *    
 *    @Override
 *    protected void onDestroy() {
 *    	  super.onDestroy();
 *        if (mAnimationDialog != null) {
 *            mAnimationDialog.destory();
 *            mAnimationDialog = null;
 *        }
 *    }
 * }
 *
 * Created by justin on 16/12/4.
 */
public abstract class AnimationDialog extends Dialog {

    public AnimationDialog(Activity context) {
        this(context, R.style.Animation_Dialog);
    }

	public AnimationDialog(Activity context, int theme) {
		super(context, theme);
		init(context);
	}

	/**
	 * 自行设置dialog位置。
	 * 
	 */
	public void setDialogLocation(int gravity, int x, int y) {
		Window window = this.getWindow();
		window.setGravity(gravity);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = LayoutParams.MATCH_PARENT;
		lp.height = LayoutParams.WRAP_CONTENT;
		lp.x = x; // 新位置X坐标
		lp.y = y; // 新位置Y坐标
		window.setAttributes(lp);
	}
	
	public void setLocationBottom() {
		setDialogLocation(Gravity.BOTTOM, 0, 0);
	}

	public void setLocationMiddle() {
		setDialogLocation(Gravity.CENTER, 0, 0);
	}

    private void init(Context context) {
        View view = obtainView(context);
        setContentView(view);
        setLocationBottom();
        setCanceledOnTouchOutside(true);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
				destroy();
            }
        });
    }

    public void destroy() {
    }

    /** 初始化布局 **/
    protected abstract View obtainView(Context context);
}
