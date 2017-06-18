package me.maximpestryakov.tinkoffnews.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import model.Title;

@StateStrategyType(AddToEndSingleStrategy.class)
interface MainView extends MvpView {

    void setNewsTitles(List<Title> newsTitles);
}
