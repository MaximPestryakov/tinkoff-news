package me.maximpestryakov.tinkoffnews.news_content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.tinkoffnews.R;
import me.maximpestryakov.tinkoffnews.Utils;
import me.maximpestryakov.tinkoffnews.model.News;

public class NewsContentActivity extends MvpAppCompatActivity implements NewsContentView {

    private static final String EXTRA_NEWS = "EXTRA_NEWS";

    public static Intent getStartIntent(Context context, News news) {
        return new Intent(context, NewsContentActivity.class).putExtra(EXTRA_NEWS, news);
    }

    @InjectPresenter
    NewsContentPresenter presenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);

        News news = (News) getIntent().getSerializableExtra(EXTRA_NEWS);

        title.setText(Utils.fromHtml(news.getTitle()));

        presenter.loadContent(news.getId());
    }

    @Override
    public void setNews(News news) {
        title.setText(Utils.fromHtml(news.getTitle()));
        content.setText(Utils.fromHtml(news.getContent()));
    }

    @Override
    public void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(int messageId) {
        Snackbar.make(findViewById(android.R.id.content), messageId, Snackbar.LENGTH_LONG).show();
    }
}
