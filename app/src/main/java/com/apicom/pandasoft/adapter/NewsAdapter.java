package com.apicom.pandasoft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apicom.pandasoft.R;
import com.apicom.pandasoft.model.NewsModel;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomViewHolder> {

    private Context context;
    private List<NewsModel> dataList;
    private OnNewsClickListener onNewsClickListener;

    public NewsAdapter(Context context, List<NewsModel> dataList, OnNewsClickListener onNewsClickListener){
        this.context = context;
        this.dataList = dataList;
        this.onNewsClickListener = onNewsClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_row,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.setNewsItem(dataList.get(position));
        holder.setOnNewsClickListener(onNewsClickListener);

        holder.txtTitle.setText(dataList.get(position).getTitle());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgTitle);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        private NewsModel newsItem;
        private final View mView;

        private TextView txtTitle;
        private ImageView imgTitle;

        private OnNewsClickListener onNewsClickListener;

        public CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.txtTitle);
            imgTitle = mView.findViewById(R.id.imgTitle);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNewsClickListener.onClick(newsItem);
                }
            });
        }

        public void setOnNewsClickListener(OnNewsClickListener onNewsClickListener) {
            this.onNewsClickListener = onNewsClickListener;
        }

        public void setNewsItem(NewsModel newsItem) {
            this.newsItem = newsItem;
        }
    }

    public interface OnNewsClickListener{
        void onClick(NewsModel item);
    }
}
