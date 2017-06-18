package repository;

import java.util.List;

import api.TinkoffNewsService;
import io.reactivex.Observable;
import model.TinkoffResponse;
import model.Title;
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

    public Observable<List<Title>> getNewsTitles() {
        return service.getNewsTitles().map(TinkoffResponse::getPayload);
    }
}
