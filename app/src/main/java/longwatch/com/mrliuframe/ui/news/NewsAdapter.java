package longwatch.com.mrliuframe.ui.news;

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
import longwatch.com.mrliuframe.bean.NewsBean;

/**
 * Created by a on 2017/2/14.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<NewsBean.DataEntity> list;
    private Context context;
    public final int TEXT_CONTENT = 0;
    public final int SMALL_IMAGE = 1;
    public final int MIDDLE_IMAGE = 2;
    public final int LARGE_IMAGE = 3;
    private SelectItemClickListener itemClickListener;
    public NewsAdapter(List<NewsBean.DataEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).isHas_image()==false){

            return TEXT_CONTENT;
        }else{
            if(list.get(position).getImage_list().size()==3){

                return SMALL_IMAGE;
            }else if(list.get(position).getLarge_image_list().size()>0){

                return LARGE_IMAGE;
            }else if(list.get(position).getMiddle_image()!=null){

                return MIDDLE_IMAGE;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TEXT_CONTENT){
            View view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
            return new MyViewHolder(view);
        }
        if(viewType==SMALL_IMAGE){
            View v = LayoutInflater.from(context).inflate(R.layout.news_item_small,parent,false);
            return new MySmallImgHolder(v);
        }
        if(viewType==MIDDLE_IMAGE){
            View v = LayoutInflater.from(context).inflate(R.layout.news_item_middle,parent,false);
            return new MyMiddleImgHolder(v);
        }
        if(viewType==LARGE_IMAGE){
            View v = LayoutInflater.from(context).inflate(R.layout.news_item_large,parent,false);
            return new MyLargeImgHolder(v);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==TEXT_CONTENT){
            MyViewHolder myViewHolder= (MyViewHolder) holder;
            myViewHolder.tv.setText(list.get(position).getTitle());
            itemClickListener.onclick(myViewHolder.itemView,position);
        }else if(getItemViewType(position)==SMALL_IMAGE){
            MySmallImgHolder mySmallImgHolder= (MySmallImgHolder) holder;
            mySmallImgHolder.tv.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getImage_list().get(0).url).centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade().into(mySmallImgHolder.imageViewOne);
            Glide.with(context).load(list.get(position).getImage_list().get(1).url).centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade().into(mySmallImgHolder.imageViewTwo);
            Glide.with(context).load(list.get(position).getImage_list().get(2).url).centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade().into(mySmallImgHolder.imageViewThree);
            itemClickListener.onclick(mySmallImgHolder.itemView,position);
        }else if(getItemViewType(position)==MIDDLE_IMAGE){
            MyMiddleImgHolder myMiddleImgHolder= (MyMiddleImgHolder) holder;
            myMiddleImgHolder.tv.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getMiddle_image().url).centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade().into(myMiddleImgHolder.imageView);
            itemClickListener.onclick(myMiddleImgHolder.itemView,position);
        }else if(getItemViewType(position)==LARGE_IMAGE){
            MyLargeImgHolder myLargeImgHolder= (MyLargeImgHolder) holder;
            myLargeImgHolder.tv.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getLarge_image_list().get(0).url).centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade().into(myLargeImgHolder.imageView);
            itemClickListener.onclick(myLargeImgHolder.itemView,position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private  TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.news_item_title);
        }
    }
    public class MySmallImgHolder extends RecyclerView.ViewHolder {

        private  TextView tv;
        private ImageView imageViewOne;
        private ImageView imageViewTwo;
        private ImageView imageViewThree;
        public MySmallImgHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.news_item_small_title);
            imageViewOne=(ImageView)itemView.findViewById(R.id.news_item_small_img_one);
            imageViewTwo=(ImageView)itemView.findViewById(R.id.news_item_small_img_two);
            imageViewThree=(ImageView)itemView.findViewById(R.id.news_item_small_img_three);
        }
    }
    public class MyMiddleImgHolder extends RecyclerView.ViewHolder {

        private  TextView tv;
        private ImageView imageView;
        public MyMiddleImgHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.news_item_middle_title);
            imageView=(ImageView)itemView.findViewById(R.id.news_item_middle_img);
        }
    }
    public class MyLargeImgHolder extends RecyclerView.ViewHolder {

        private  TextView tv;
        private ImageView imageView;
        public MyLargeImgHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.news_item_large_title);
            imageView=(ImageView)itemView.findViewById(R.id.news_item_large_img);
        }
    }
    public void setClickCallbackListener(SelectItemClickListener listener) {
        itemClickListener = listener;
    }
    public interface SelectItemClickListener{
        void onclick(View itemView, int position);
    }
}
