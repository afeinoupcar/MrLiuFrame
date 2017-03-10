package longwatch.com.mrliuframe.ui.shop;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.bean.ShopBean;
import longwatch.com.mrliuframe.customview.GlideRoundTransform;

/**
 * Created by a on 2017/2/22.
 */

public class ShopRvAdapter extends RecyclerView.Adapter<ShopRvAdapter.MyShopViewHolder> {
    private Context context;
    private List<ShopBean.DataEntity> list = new ArrayList<>();
    private SelectItemClickListener itemClickListener;
    public ShopRvAdapter(Context context, List<ShopBean.DataEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_rv_item, parent, false);
        return new MyShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyShopViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getGoods_img()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().transform(new GlideRoundTransform(context)).into(holder.imag);
        holder.name.setText(list.get(position).getGoods_name());
        holder.xianjia.setText("现价：" + list.get(position).getShop_price());
        holder.yuanjia.setText("原价：" + list.get(position).getMarket_price());
        holder.yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        itemClickListener.onclick(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyShopViewHolder extends RecyclerView.ViewHolder {

        private ImageView imag;
        private TextView name;
        private TextView yuanjia;
        private TextView xianjia;

        public MyShopViewHolder(View itemView) {
            super(itemView);
            imag = (ImageView) itemView
                    .findViewById(R.id.item_gv_image);
            name = (TextView) itemView.findViewById(R.id.item_gv_name);
            yuanjia = (TextView) itemView
                    .findViewById(R.id.item_gv_yuanjia);
            xianjia = (TextView) itemView
                    .findViewById(R.id.item_gv_xianjia);
        }
    }
    public void setClickCallbackListener(SelectItemClickListener listener) {
        itemClickListener = listener;
    }
    public interface SelectItemClickListener{
        void onclick(View itemView, int position);
    }
}
