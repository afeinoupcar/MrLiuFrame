package longwatch.com.mrliuframe.ui.shop;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.XQBean;

public class OneListAdapter extends BaseAdapter {
	private List<XQBean.DataBean.GoodsBean.AttributesBean> li;
	private Context context;

	public OneListAdapter(List<XQBean.DataBean.GoodsBean.AttributesBean> li, Context context) {
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
		View view = View.inflate(context, R.layout.one_list_item, null);
		TextView tv1 = (TextView) view.findViewById(R.id.xq_one_tv1);
		TextView tv2 = (TextView) view.findViewById(R.id.xq_one_tv2);
		tv1.setText(li.get(position).getAttr_name());
		tv2.setText(li.get(position).getAttr_value());
		return view;
	}

}
