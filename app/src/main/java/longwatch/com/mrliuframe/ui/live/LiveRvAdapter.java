package longwatch.com.mrliuframe.ui.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.RmBean;
import longwatch.com.mrliuframe.customview.GlideCircleTransform;
import longwatch.com.mrliuframe.customview.GlideRoundTransform;
import longwatch.com.mrliuframe.util.CircleImageView;

/**
 * Created by a on 2017/2/21.
 */

public class LiveRvAdapter extends RecyclerView.Adapter<LiveRvAdapter.LiveViewHolder>{
    private List<RmBean.ContentBean.ListBean> lb;
    private Context context;
    private SelectItemClickListener itemClickListener;
    public LiveRvAdapter(Context context, List<RmBean.ContentBean.ListBean> lb) {
        this.context = context;
        this.lb = lb;
    }
    @Override
    public LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rm_lv_item,parent,false);
        return new LiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveViewHolder holder, int position) {
        Glide.with(context).load(lb.get(position).getBigheadimg()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().transform(new GlideRoundTransform(context)).into(holder.backimage);
        Glide.with(context).load(lb.get(position).getSmallheadimg()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().transform(new GlideCircleTransform(context)).into(holder.image);
       holder. name.setText(lb.get(position).getName());
        holder.city.setText(lb.get(position).getPlace());
        holder. online.setText("LIVE "+lb.get(position).getOnline());
        if (!lb.get(position).getLivename().equals("")) {
            holder.qianming.setVisibility(View.VISIBLE);
            holder. qianming.setText(lb.get(position).getLivename());
        }
        itemClickListener.onclick(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return lb.size();
    }

    public class LiveViewHolder extends RecyclerView.ViewHolder{

        private  ImageView backimage;
        private  CircleImageView image;
        private  TextView name;
        private  TextView city;
        private  TextView online;
        private  TextView qianming;

        public LiveViewHolder(View view) {
            super(view);
            backimage = (ImageView) view.findViewById(R.id.rm_lv_item_backimag);
            image = (CircleImageView) view.findViewById(R.id.rm_lv_item_imag);
            name = (TextView) view.findViewById(R.id.rm_lv_item_name);
            city = (TextView) view.findViewById(R.id.rm_lv_item_city);
            online = (TextView) view.findViewById(R.id.rm_lv_item_online);
            qianming = (TextView) view.findViewById(R.id.rm_lv_item_qianming);
        }
    }
    public void setClickCallbackListener(SelectItemClickListener listener) {
        itemClickListener = listener;
    }
    public interface SelectItemClickListener{
        void onclick(View itemView, int position);
    }
}
