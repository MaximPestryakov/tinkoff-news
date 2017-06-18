package me.maximpestryakov.tinkoffnews.main;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.tinkoffnews.R;
import model.Title;

import static android.text.Html.FROM_HTML_MODE_COMPACT;
import static android.text.Html.FROM_HTML_MODE_LEGACY;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.NewsTitleViewHolder> {

    private List<Title> newsTitles;

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

    void setNewsTitles(List<Title> newsTitles) {
        this.newsTitles = newsTitles;
        notifyDataSetChanged();
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
            Title newsTitle = newsTitles.get(position);
            title.setText(Html.fromHtml(newsTitle.getText()));
            date.setText(String.valueOf(newsTitle.getPublicationDate()));
        }
    }
}
