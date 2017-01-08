package com.example.adaanahmed.westerosatwar.search_screen;

import com.example.adaanahmed.westerosatwar.base.BasePresenter;
import com.example.adaanahmed.westerosatwar.base.BaseView;

import java.util.ArrayList;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class SearchScreenContract {
    interface View extends BaseView {
        void setData(ArrayList<SearchModel> searchModels);
    }

    interface Presenter extends BasePresenter<View> {
        void startKingProfileActivity(String kingName);

        void fetchingNames(String str);
    }
}
