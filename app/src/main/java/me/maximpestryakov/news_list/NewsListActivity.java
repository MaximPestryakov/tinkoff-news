package me.maximpestryakov.news_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.model.News;
import me.maximpestryakov.news_content.NewsContentActivity;
import me.maximpestryakov.tinkoffnews.R;

public class NewsListActivity extends MvpAppCompatActivity implements NewsListView {

    @InjectPresenter
    NewsListPresenter presenter;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.newsList)
    RecyclerView newsList;

    private NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_news_list_activity);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(presenter::update);

        adapter = new NewsListAdapter();
        adapter.setOnNewsClickListener(newsTitle -> {
            Intent intent = NewsContentActivity.getStartIntent(this, newsTitle);
            startActivity(intent);
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        newsList.setLayoutManager(layoutManager);
        newsList.setAdapter(adapter);
        newsList.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefresh.setRefreshing(refreshing);
    }

    @Override
    public void setNewsTitles(List<News.Title> newsTitles) {
        adapter.setNewsTitles(newsTitles);
    }
}
