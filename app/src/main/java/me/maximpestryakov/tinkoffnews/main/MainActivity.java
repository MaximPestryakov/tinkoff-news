package me.maximpestryakov.tinkoffnews.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.tinkoffnews.R;
import model.Title;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.newsList)
    RecyclerView newsList;

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(presenter::update);

        adapter = new MainAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        newsList.setLayoutManager(layoutManager);
        newsList.setAdapter(adapter);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefresh.setRefreshing(refreshing);
    }

    @Override
    public void setNewsTitles(List<Title> newsTitles) {
        adapter.setNewsTitles(newsTitles);
    }
}
