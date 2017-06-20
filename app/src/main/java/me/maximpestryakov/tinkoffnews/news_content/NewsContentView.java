package me.maximpestryakov.tinkoffnews.news_content;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import me.maximpestryakov.tinkoffnews.model.News;

interface NewsContentView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setNews(News news);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setLoading(boolean loading);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(int messageId);
}
