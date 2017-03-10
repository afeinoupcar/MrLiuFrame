package longwatch.com.mrliuframe.ui.live;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.RmBean;
import longwatch.com.mrliuframe.ui.account.WebViewActivity;

/**
 * Created by 刘腾飞 on 2016/10/9.
 */

public class RmVpAdapter extends PagerAdapter {
    private List<RmBean.ContentBean.BannerBean> bb;
    private Context context;
    private Handler handler;

    public RmVpAdapter(List<RmBean.ContentBean.BannerBean> bb, Context context, Handler handler) {
        this.bb = bb;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.rm_vp_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.rm_vp_item_image);
        Glide.with(context).load(bb.get(position % bb.size()).getImg()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url", bb.get(position%bb.size()).getUrl());
                context.startActivity(intent);
            }
        });
        image.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // 手指按下的事件
                        // 全部取消
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP: // 手指抬起的事件
                        handler.sendEmptyMessageDelayed(1, 2000);
                        break;

                    case MotionEvent.ACTION_CANCEL: // 取消的事件
                        handler.sendEmptyMessageDelayed(1, 2000);
                        break;

                }

                return false;
            }
        });
        container.addView(view);
        return view;
    }
}
