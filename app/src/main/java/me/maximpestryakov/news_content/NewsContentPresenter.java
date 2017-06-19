package me.maximpestryakov.news_content;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.maximpestryakov.repository.TinkoffNewsRepository;

@InjectViewState
public class NewsContentPresenter extends MvpPresenter<NewsContentView> {

    private final TinkoffNewsRepository repository = new TinkoffNewsRepository();

    void loadContent(String id) {
        getViewState().setLoading(true);
        repository.getNewsContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                    getViewState().setNews(news);
                    getViewState().setLoading(false);
                }, throwable -> {
                });
    }
}
