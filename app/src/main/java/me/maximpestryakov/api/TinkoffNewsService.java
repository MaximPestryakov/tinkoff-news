package me.maximpestryakov.api;

import java.util.List;

import io.reactivex.Observable;
import me.maximpestryakov.model.News;
import me.maximpestryakov.model.TinkoffResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TinkoffNewsService {

    String URL = " https://api.tinkoff.ru/v1/";

    @GET("news")
    Observable<TinkoffResponse<List<News.Title>>> getNewsTitles();

    @GET("news_content")
    Observable<TinkoffResponse<News>> getNewsContent(@Query("id") String id);
}
