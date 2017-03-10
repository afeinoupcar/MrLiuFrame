package longwatch.com.mrliuframe.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import longwatch.com.mrliuframe.R;


public class SquareImageView extends ImageView {

	private float multiple = 1.0f;
	public SquareImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SquareImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
		multiple = a.getFloat(R.styleable.CustomView_multiple, 1.0f);
		a.recycle();
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		widthMeasureSpec = heightMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		if (multiple != 1.0f) {
			heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (multiple * width), MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}
