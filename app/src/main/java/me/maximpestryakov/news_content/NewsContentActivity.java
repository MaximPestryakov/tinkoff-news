package me.maximpestryakov.news_content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.Utils;
import me.maximpestryakov.model.News;
import me.maximpestryakov.tinkoffnews.R;

public class NewsContentActivity extends MvpAppCompatActivity implements NewsContentView {

    private static final String EXTRA_NEWS_TITLE = "EXTRA_NEWS_TITLE";

    public static Intent getStartIntent(Context context, News.Title newsTitle) {
        return new Intent(context, NewsContentActivity.class).putExtra(EXTRA_NEWS_TITLE, newsTitle);
    }

    @InjectPresenter
    NewsContentPresenter presenter;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);

        News.Title newsTitle = (News.Title) getIntent().getSerializableExtra(EXTRA_NEWS_TITLE);

        title.setText(Utils.fromHtml(newsTitle.getText()));

        presenter.loadContent(newsTitle.getId());
    }

    @Override
    public void setNews(News news) {
        title.setText(Utils.fromHtml(news.getTitle().getText()));
        content.setText(Utils.fromHtml(news.getContent()));
    }
}
