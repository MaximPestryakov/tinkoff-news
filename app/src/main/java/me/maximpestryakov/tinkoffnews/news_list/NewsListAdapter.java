package me.maximpestryakov.tinkoffnews.news_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.tinkoffnews.R;
import me.maximpestryakov.tinkoffnews.Utils;
import me.maximpestryakov.tinkoffnews.model.News;

class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {

    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    private List<News> newsList;

    private OnNewsClickListener onNewsClickListener;

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_title, parent, false);
        return new NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (newsList == null) {
            return 0;
        }
        return newsList.size();
    }

    void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    void setOnNewsClickListener(OnNewsClickListener onNewsClickListener) {
        this.onNewsClickListener = onNewsClickListener;
    }

    class NewsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.date)
        TextView date;

        NewsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            News news = newsList.get(position);
            itemView.setOnClickListener(v -> onNewsClickListener.onNewsClick(news));
            title.setText(Utils.fromHtml(news.getTitle()));
            date.setText(dateFormat.format(new Date(news.getDate())));
        }
    }

    interface OnNewsClickListener {

        void onNewsClick(News news);
    }
}
