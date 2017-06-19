package me.maximpestryakov.tinkoffnews.news_list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.maximpestryakov.tinkoffnews.repository.TinkoffNewsRepository;

@InjectViewState
public class NewsListPresenter extends MvpPresenter<NewsListView> {

    private final TinkoffNewsRepository repository = new TinkoffNewsRepository();

    NewsListPresenter() {
        update();
    }

    void update() {
        getViewState().setRefreshing(true);
        repository.getNewsTitles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsTitles -> {
                    getViewState().setNewsTitles(newsTitles);
                    getViewState().setRefreshing(false);
                }, throwable -> {
                });
    }
}
