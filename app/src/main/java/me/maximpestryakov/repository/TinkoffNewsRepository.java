package me.maximpestryakov.repository;

import java.util.List;

import io.reactivex.Observable;
import me.maximpestryakov.api.TinkoffNewsService;
import me.maximpestryakov.model.News;
import me.maximpestryakov.model.TinkoffResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TinkoffNewsRepository {

    private TinkoffNewsService service;

    public TinkoffNewsRepository() {
        service = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TinkoffNewsService.URL)
                .build()
                .create(TinkoffNewsService.class);
    }

    public Observable<List<News.Title>> getNewsTitles() {
        return service.getNewsTitles().map(TinkoffResponse::getPayload);
    }

    public Observable<News> getNewsContent(String id) {
        return service.getNewsContent(id).map(TinkoffResponse::getPayload);
    }
}
