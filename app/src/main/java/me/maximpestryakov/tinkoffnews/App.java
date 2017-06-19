package me.maximpestryakov.tinkoffnews;

import android.app.Application;

import io.realm.Realm;
import me.maximpestryakov.tinkoffnews.di.AppComponent;
import me.maximpestryakov.tinkoffnews.di.AppModule;
import me.maximpestryakov.tinkoffnews.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
