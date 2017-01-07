package com.example.adaanahmed.westerosatwar.splash_screen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.adaanahmed.westerosatwar.base.BaseActivity;
import com.example.adaanahmed.westerosatwar.network.ResponseModel;

import java.util.ArrayList;

public class SplashActivity extends BaseActivity implements SplashScreenContract.View {

    SplashScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.view.View decorView = getWindow().getDecorView();
        int decorFlags = android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | android.view.View.SYSTEM_UI_FLAG_FULLSCREEN | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;

        if(null != decorView) {
            decorView.setSystemUiVisibility(decorFlags);
        }

        presenter = new SplashScreenPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();

    }

    @Override
    protected void handleIntent() {
        // no intent to handle
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void showIndicator() {

    }

    @Override
    public void setDataSuccess(ArrayList<ResponseModel> responseModels) {
        showToast(responseModels.get(0).toString());
    }

    @Override
    public void setDataFailure(String string) {
        Log.e(this.getClass().getName(), string);
        showToast(string);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
