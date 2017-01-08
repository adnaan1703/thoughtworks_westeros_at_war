package com.example.adaanahmed.westerosatwar.profile_screen;

import com.example.adaanahmed.westerosatwar.base.BasePresenter;
import com.example.adaanahmed.westerosatwar.base.BaseView;

import java.util.ArrayList;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class ProfileScreenContract {

    interface Presenter extends BasePresenter<View> {

    }

    interface View extends BaseView {
        void setData(ArrayList<ProfileScreenListModel> profileScreenListModels);
    }

}
