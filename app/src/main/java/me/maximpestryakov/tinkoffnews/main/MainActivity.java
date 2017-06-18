package me.maximpestryakov.tinkoffnews.main;

import android.os.Bundle;
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

    @BindView(R.id.newsList)
    RecyclerView newsList;

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MainAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        newsList.setLayoutManager(layoutManager);
        newsList.setAdapter(adapter);
    }

    @Override
    public void setNewsTitles(List<Title> newsTitles) {
        adapter.setNewsTitles(newsTitles);
    }
}
