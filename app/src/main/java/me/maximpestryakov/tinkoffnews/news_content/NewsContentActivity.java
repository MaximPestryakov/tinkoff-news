package me.maximpestryakov.tinkoffnews.news_content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.tinkoffnews.R;
import me.maximpestryakov.tinkoffnews.Utils;
import me.maximpestryakov.tinkoffnews.model.News;

public class NewsContentActivity extends MvpAppCompatActivity implements NewsContentView {

    private static final String EXTRA_ID = "EXTRA_ID";

    public static Intent getStartIntent(Context context, String id) {
        return new Intent(context, NewsContentActivity.class).putExtra(EXTRA_ID, id);
    }

    @InjectPresenter
    NewsContentPresenter presenter;

    @ProvidePresenter
    NewsContentPresenter provideNewsContentPresenter() {
        String id = getIntent().getStringExtra(EXTRA_ID);
        return new NewsContentPresenter(id);
    }

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_news_content);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
