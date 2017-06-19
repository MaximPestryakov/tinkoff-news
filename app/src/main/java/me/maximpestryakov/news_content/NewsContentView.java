package me.maximpestryakov.news_content;

import com.arellomobile.mvp.MvpView;

import me.maximpestryakov.model.News;

interface NewsContentView extends MvpView {

    void setNews(News news);
}
