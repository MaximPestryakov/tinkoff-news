package me.maximpestryakov.tinkoffnews.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import me.maximpestryakov.tinkoffnews.App;
import me.maximpestryakov.tinkoffnews.api.TinkoffNewsService;
import me.maximpestryakov.tinkoffnews.model.News;
import me.maximpestryakov.tinkoffnews.model.TinkoffResponse;

public class TinkoffNewsRepository {

    @Inject
    TinkoffNewsService service;

    @Inject
    Realm realm;

    public TinkoffNewsRepository() {
        App.getComponent().inject(this);
    }

    public Observable<List<News>> getNewsList() {
        return service.getNewsList().map(TinkoffResponse::getPayload);
    }

    public Observable<News> getNewsContent(String id) {
        return service.getNewsContent(id).map(TinkoffResponse::getPayload);
    }
}
