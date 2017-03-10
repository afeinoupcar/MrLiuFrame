package longwatch.com.mrliuframe.ui.music;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
import longwatch.com.mrliuframe.bean.MusicTopBean;
import longwatch.com.mrliuframe.customview.GlideCircleTransform;

/**
 * Created by a on 2017/2/23.
 */

public class MusicTopAdapter extends RecyclerView.Adapter<MusicTopAdapter.MyMusicTopViewHolder> {
    private Context context;
    private List<MusicTopBean.ShowapiResBodyEntity.PagebeanEntity.SonglistEntity> list = new ArrayList<>();
    private SelectItemClickListener itemClickListener;
    public MusicTopAdapter(Context context, List<MusicTopBean.ShowapiResBodyEntity.PagebeanEntity.SonglistEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyMusicTopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_top_item, parent, false);
        return new MyMusicTopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyMusicTopViewHolder holder, int position) {
        holder.number.setText((position + 1)+"." );
            holder.number.setTextColor(Color.GRAY);
            holder.number.setTypeface(Typeface.DEFAULT_BOLD);

        Glide.with(context).load(list.get(position).getAlbumpic_small()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().transform(new GlideCircleTransform(context)).into(holder.image);
        holder.song.setText(list.get(position).getSongname());
        holder.song.setTypeface(Typeface.DEFAULT_BOLD);
        holder.singer.setText(list.get(position).getSingername());
        itemClickListener.onclick(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyMusicTopViewHolder extends RecyclerView.ViewHolder {
        private TextView number;
        private ImageView image;
        private TextView song;
        private TextView singer;

        public MyMusicTopViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.music_top_item_number);
            image = (ImageView) itemView.findViewById(R.id.music_top_item_imag);
            song = (TextView) itemView.findViewById(R.id.music_top_item_song);
            singer = (TextView) itemView.findViewById(R.id.music_top_item_singer);
        }
    }
    public void setClickCallbackListener(SelectItemClickListener listener) {
        itemClickListener = listener;
    }
    public interface SelectItemClickListener{
        void onclick(View itemView, int position);
    }
}
