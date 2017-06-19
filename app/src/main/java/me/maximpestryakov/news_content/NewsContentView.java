package me.maximpestryakov.news_content;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import me.maximpestryakov.model.News;

@StateStrategyType(AddToEndSingleStrategy.class)
interface NewsContentView extends MvpView {

    void setNews(News news);

    void setLoading(boolean loading);
}
