package me.maximpestryakov.tinkoffnews.news_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import me.maximpestryakov.tinkoffnews.model.News;

@StateStrategyType(AddToEndSingleStrategy.class)
interface NewsListView extends MvpView {

    void setRefreshing(boolean refreshing);

    void setNewsTitles(List<News.Title> newsTitles);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(int messageId);
}
