package com.example.adaanahmed.westerosatwar.home_screen;

import com.example.adaanahmed.westerosatwar.base.BasePresenter;
import com.example.adaanahmed.westerosatwar.base.BaseView;
import com.example.adaanahmed.westerosatwar.dbUtil.models.King;

import java.util.ArrayList;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class HomeScreenContract {

    interface Presenter extends BasePresenter<View> {
        int ROW_ITEMS_COUNT = 10;

        void fetchData();

        void startSearchActivity();

        void startFilterFragment();

        void startKingProfileActivity(King king);

        int getTotalCount();
    }

    interface View extends BaseView {

        void updateList(ArrayList<King> kings, boolean isComplete);
    }
}
