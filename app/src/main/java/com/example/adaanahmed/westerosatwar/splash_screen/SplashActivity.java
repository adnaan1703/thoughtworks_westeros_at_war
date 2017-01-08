package com.example.adaanahmed.westerosatwar.splash_screen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.UIWidget.ProximaTextView;
import com.example.adaanahmed.westerosatwar.base.BaseActivity;

public class SplashActivity extends BaseActivity implements SplashScreenContract.View {

    private SplashScreenContract.Presenter presenter;
    private ProgressBar progressBar;
    private ProximaTextView proximaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        android.view.View decorView = getWindow().getDecorView();
        int decorFlags = android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | android.view.View.SYSTEM_UI_FLAG_FULLSCREEN | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;

        if (null != decorView) {
            decorView.setSystemUiVisibility(decorFlags);
        }

        presenter = new SplashScreenPresenter(this);
        progressBar = (ProgressBar) findViewById(R.id.splash_progressbar);
        proximaTextView = (ProximaTextView) findViewById(R.id.splash_text);
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
    public void onDataStoreSuccess() {
        presenter.navigateToHomeScreen();
    }

    @Override
    public void onDataStoreFailure(String msg) {
        showToast(msg, true);
        Log.e(this.getClass().getName(), msg);
    }

    @Override
    public void onDataFetchSuccess() {
        proximaTextView.setText(R.string.processing_data);
    }

    @Override
    public void onDataFetchFailure(String msg) {
        Log.e(this.getClass().getName(), msg);
        showToast(msg);
        progressBar.setVisibility(View.INVISIBLE);
        proximaTextView.setText(R.string.check_internet);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
