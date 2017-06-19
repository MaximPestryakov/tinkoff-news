package me.maximpestryakov.tinkoffnews.news_list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.maximpestryakov.tinkoffnews.Utils;
import me.maximpestryakov.tinkoffnews.repository.TinkoffNewsRepository;

@InjectViewState
public class NewsListPresenter extends MvpPresenter<NewsListView> {

    private final TinkoffNewsRepository repository = new TinkoffNewsRepository();

    private final CompositeDisposable disposables = new CompositeDisposable();

    NewsListPresenter() {
        update();
    }

    void update() {
        getViewState().setRefreshing(true);
        Disposable disposable = repository.getNewsTitles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsTitles -> {
                    getViewState().setNewsTitles(newsTitles);
                    getViewState().setRefreshing(false);
                }, throwable -> {
                    getViewState().showError(Utils.parseError(throwable));
                    getViewState().setRefreshing(false);
                });
        disposables.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }
}
