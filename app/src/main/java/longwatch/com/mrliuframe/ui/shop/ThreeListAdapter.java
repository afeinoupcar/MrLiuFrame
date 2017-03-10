package longwatch.com.mrliuframe.ui.shop;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.ImageBean;
import longwatch.com.mrliuframe.customview.GlideRoundTransform;


public class ThreeListAdapter extends BaseAdapter {
    private List<ImageBean> li;
    private Context context;

    public ThreeListAdapter(List<ImageBean> li, Context context) {
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
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return li.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.three_list_item, null);
        ImageView imag = (ImageView) view.findViewById(R.id.xq_three_imag);
        Glide.with(context).load(li.get(position).getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().transform(new GlideRoundTransform(context)).into(imag);
        return view;
    }

}
