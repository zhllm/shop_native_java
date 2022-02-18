package com.example.anative.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.anative.R;
import com.example.anative.beans.Goods;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<Goods> data;
    private final LayoutInflater inflater;

    public HomeRecycleViewAdapter(Context context, List<Goods> goods) {
        this.context = context;
        this.data = goods;
        inflater = LayoutInflater.from(context);
    }

    public void setGoods(List<Goods> goods) {
        this.data.clear();
        this.data.addAll(goods);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Goods goods = data.get(position);
        if (goods.getBanners() != null) {
            // banner
            return R.layout.home_recycleview_banner;
        } else if (goods.getImageUrl() == null) {
            return R.layout.home_recycleview_text;
            // 文字
        } else if (goods.getText() == null) {
            // 显示图片
            return R.layout.home_recycleview_image;
        } else {
            // 文字加图片
            return R.layout.home_recycleview_text_and_imageview;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        if (viewType == R.layout.home_recycleview_text_and_imageview) {
            return new MultiViewHolder(view);
        }
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        Goods goods = data.get(position);
        switch (itemViewType) {
            case R.layout.home_recycleview_banner:
                ((Banner) holder.itemView).setAdapter(new BannerImageAdapter<String>(goods.getBanners()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                        Glide.with(holder.itemView)
                                .load(data)
                                .apply(RequestOptions.centerCropTransform())
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver((LifecycleOwner) context)
                        .setIndicator(new CircleIndicator(context));
                break;
            case R.layout.home_recycleview_image:
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into((ImageView) holder.itemView);
                break;
            case R.layout.home_recycleview_text:
                ((TextView) holder.itemView).setText(goods.getText());
                break;
            case R.layout.home_recycleview_text_and_imageview:
                MultiViewHolder multiViewHolder = (MultiViewHolder) holder;
                multiViewHolder.textView.setText(goods.getText());
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(multiViewHolder.imageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class SingleViewHolder extends RecyclerView.ViewHolder {
        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
