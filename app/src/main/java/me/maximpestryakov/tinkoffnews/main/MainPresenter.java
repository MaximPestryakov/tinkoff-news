package me.maximpestryakov.tinkoffnews.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import repository.TinkoffNewsRepository;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private final TinkoffNewsRepository repository = new TinkoffNewsRepository();

    MainPresenter() {
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
