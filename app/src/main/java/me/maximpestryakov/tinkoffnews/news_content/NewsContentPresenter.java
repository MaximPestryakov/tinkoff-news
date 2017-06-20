package me.maximpestryakov.tinkoffnews.news_content;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import me.maximpestryakov.tinkoffnews.App;
import me.maximpestryakov.tinkoffnews.Utils;
import me.maximpestryakov.tinkoffnews.model.News;
import me.maximpestryakov.tinkoffnews.repository.TinkoffNewsRepository;

@InjectViewState
public class NewsContentPresenter extends MvpPresenter<NewsContentView> {

    @Inject
    TinkoffNewsRepository repository;

    @Inject
    Realm realm;

    @Inject
    CompositeDisposable disposables;

    NewsContentPresenter() {
        App.getComponent().inject(this);
    }

    void loadContent(String id) {
        getViewState().setLoading(true);
        Observable.just(realm.copyFromRealm(realm.where(News.class).equalTo(News.ID, id).findFirst()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(news -> news != null)
                .subscribe(news -> getViewState().setNews(news));
        Disposable disposable = repository.getNewsContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                    getViewState().setNews(news);
                    getViewState().setLoading(false);
                    realm.executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(news));
                }, throwable -> {
                    getViewState().showError(Utils.parseError(throwable));
                    getViewState().setLoading(false);
                });
        disposables.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }
}
