package me.maximpestryakov.tinkoffnews.news_content;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.maximpestryakov.tinkoffnews.Utils;
import me.maximpestryakov.tinkoffnews.repository.TinkoffNewsRepository;

@InjectViewState
public class NewsContentPresenter extends MvpPresenter<NewsContentView> {

    private final TinkoffNewsRepository repository = new TinkoffNewsRepository();

    private final CompositeDisposable disposables = new CompositeDisposable();

    void loadContent(String id) {
        getViewState().setLoading(true);
        Disposable disposable = repository.getNewsContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                    getViewState().setNews(news);
                    getViewState().setLoading(false);
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
