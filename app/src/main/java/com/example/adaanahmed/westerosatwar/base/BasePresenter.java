package com.example.adaanahmed.westerosatwar.base;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public interface BasePresenter<V extends BaseView> {
    void start();
    void stop();
    void setView(V view);
    V getView();
}
