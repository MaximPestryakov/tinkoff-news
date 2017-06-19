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

class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsTitleViewHolder> {

    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    private List<News.Title> newsTitles;

    private OnNewsClickListener onNewsClickListener;

    @Override
    public NewsTitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_title, parent, false);
        return new NewsTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsTitleViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (newsTitles == null) {
            return 0;
        }
        return newsTitles.size();
    }

    void setNewsTitles(List<News.Title> newsTitles) {
        this.newsTitles = newsTitles;
        notifyDataSetChanged();
    }

    void setOnNewsClickListener(OnNewsClickListener onNewsClickListener) {
        this.onNewsClickListener = onNewsClickListener;
    }

    class NewsTitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.date)
        TextView date;

        NewsTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            News.Title newsTitle = newsTitles.get(position);
            itemView.setOnClickListener(v -> onNewsClickListener.onNewsClick(newsTitle));
            title.setText(Utils.fromHtml(newsTitle.getText()));
            date.setText(dateFormat.format(new Date(newsTitle.getPublicationDate())));
        }
    }

    interface OnNewsClickListener {

        void onNewsClick(News.Title newsTitle);
    }
}
