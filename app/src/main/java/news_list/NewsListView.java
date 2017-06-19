package news_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import model.Title;

@StateStrategyType(AddToEndSingleStrategy.class)
interface NewsListView extends MvpView {

    void setRefreshing(boolean refreshing);

    void setNewsTitles(List<Title> newsTitles);
}
