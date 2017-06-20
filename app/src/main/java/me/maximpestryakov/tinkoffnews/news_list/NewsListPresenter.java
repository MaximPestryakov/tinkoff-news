package me.maximpestryakov.tinkoffnews.news_list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.Sort;
import me.maximpestryakov.tinkoffnews.App;
import me.maximpestryakov.tinkoffnews.Utils;
import me.maximpestryakov.tinkoffnews.model.News;
import me.maximpestryakov.tinkoffnews.repository.TinkoffNewsRepository;

@InjectViewState
public class NewsListPresenter extends MvpPresenter<NewsListView> {

    @Inject
    TinkoffNewsRepository repository;

    @Inject
    Realm realm;

    private final CompositeDisposable disposables = new CompositeDisposable();

    NewsListPresenter() {
        App.getComponent().inject(this);
        Observable.just(realm.where(News.class).findAllSorted(News.DATE, Sort.DESCENDING))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(newsList -> newsList != null && !newsList.isEmpty())
                .subscribe(newsList -> getViewState().setNewsList(newsList));
        update();
    }

    void update() {
        getViewState().setRefreshing(true);
        Disposable disposable = repository.getNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsList -> {
                    getViewState().setNewsList(newsList);
                    getViewState().setRefreshing(false);
                    realm.executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(newsList));
                }, throwable -> {
                    throwable.printStackTrace();
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
