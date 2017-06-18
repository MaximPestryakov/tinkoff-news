package api;

import java.util.List;

import io.reactivex.Observable;
import model.TinkoffResponse;
import model.Title;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TinkoffNewsService {

    String URL = " https://api.tinkoff.ru/v1/";

    @GET("news")
    Observable<TinkoffResponse<List<Title>>> getNewsTitles();

    @GET("news_content")
    void getNewsContent(@Path("id") String id);
}
