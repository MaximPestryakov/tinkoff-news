package api;

import model.Title;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TinkoffNewsService {

    String URL = " https://api.tinkoff.ru/v1/";

    @GET("news")
    Call<Response<Title>> getNewsTitles();

    @GET("news_content")
    void getNewsContent(@Path("id") String id);
}
