package longwatch.com.mrliuframe.ui.shop;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.XQBean;
import longwatch.com.mrliuframe.customview.GlideRoundTransform;

public class TwoListAdapter extends BaseAdapter {
	private List<XQBean.DataBean.CommentsBean> li;
	private Context context;

	public TwoListAdapter(List<XQBean.DataBean.CommentsBean> li, Context context) {
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
		View view = View.inflate(context, R.layout.two_list_item, null);
		ImageView imag = (ImageView) view.findViewById(R.id.xq_two_image);
		TextView name = (TextView) view.findViewById(R.id.xq_two_name);
		TextView time = (TextView) view.findViewById(R.id.xq_two_time);
		TextView cont = (TextView) view.findViewById(R.id.xq_two_content);
		Glide.with(context).load(li.get(position).getUser().getIcon()).centerCrop()
				.placeholder(R.mipmap.ic_launcher)
				.crossFade().transform(new GlideRoundTransform(context)).into(imag);
		name.setText(li.get(position).getUser().getNick_name());
		time.setText(li.get(position).getCreatetime());
		cont.setText(li.get(position).getContent());

		return view;
	}

}
