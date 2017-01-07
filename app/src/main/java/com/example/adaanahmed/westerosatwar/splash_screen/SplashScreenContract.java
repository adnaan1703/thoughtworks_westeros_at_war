package com.example.adaanahmed.westerosatwar.splash_screen;

import com.example.adaanahmed.westerosatwar.base.BasePresenter;
import com.example.adaanahmed.westerosatwar.base.BaseView;
import com.example.adaanahmed.westerosatwar.network.ResponseModel;

import java.util.ArrayList;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class SplashScreenContract {
    interface Presenter extends BasePresenter {
        void navigateToHomeScreen();
    }

    interface View extends BaseView {
        void showIndicator();
        void setDataSuccess(ArrayList<ResponseModel> responseModels);
        void setDataFailure(String string);
    }
}
