package me.maximpestryakov.tinkoffnews.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;
import me.maximpestryakov.tinkoffnews.App;
import me.maximpestryakov.tinkoffnews.api.TinkoffNewsService;
import me.maximpestryakov.tinkoffnews.model.News;
import me.maximpestryakov.tinkoffnews.model.deserializer.NewsDeserializer;
import me.maximpestryakov.tinkoffnews.repository.TinkoffNewsRepository;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(News.class, new NewsDeserializer())
                .create();
    }

    @Singleton
    @Provides
    TinkoffNewsService provideTinkoffNewsService(Gson gson) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(TinkoffNewsService.URL)
                .build()
                .create(TinkoffNewsService.class);
    }

    @Singleton
    @Provides
    TinkoffNewsRepository provideTinkoffNewsRepository() {
        return new TinkoffNewsRepository();
    }

    @Singleton
    @Provides
    Context provideContext() {
        return app;
    }

    @Singleton
    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
