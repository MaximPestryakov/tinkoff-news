package me.maximpestryakov.tinkoffnews.di;

import javax.inject.Singleton;

import dagger.Component;
import me.maximpestryakov.tinkoffnews.news_content.NewsContentPresenter;
import me.maximpestryakov.tinkoffnews.news_list.NewsListPresenter;
import me.maximpestryakov.tinkoffnews.repository.TinkoffNewsRepository;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(NewsContentPresenter newsContentPresenter);

    void inject(NewsListPresenter newsListPresenter);

    void inject(TinkoffNewsRepository tinkoffNewsRepository);
}
