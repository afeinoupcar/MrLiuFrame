package longwatch.com.mrliuframe.ui.shop;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.XQBean;

public class XqPagerAdapter extends PagerAdapter {
    private List<XQBean.DataBean.GoodsBean.GalleryBean> li;
    private Context context;

    public XqPagerAdapter(List<XQBean.DataBean.GoodsBean.GalleryBean> li, Context context) {
        super();
        this.li = li;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return li.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.sy_pager_item, null);
        ImageView image = (ImageView) view
                .findViewById(R.id.sy_pager_item_image);
        Glide.with(context).load(li.get(position).getNormal_url()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().into(image);
        container.addView(view);
        return view;
    }
}
