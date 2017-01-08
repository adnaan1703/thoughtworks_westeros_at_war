package com.example.adaanahmed.westerosatwar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != getPresenter()) {
            getPresenter().start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != getPresenter()) {
            getPresenter().stop();
        }
    }

    protected void showToast(String string, boolean isLong) {
        int durations = Toast.LENGTH_SHORT;
        if (isLong) {
            durations = Toast.LENGTH_LONG;
        }

        Toast.makeText(getApplicationContext(), string, durations).show();
    }

    protected void showToast(String string) {
        showToast(string, false);
    }

    protected abstract void handleIntent();

    protected abstract BasePresenter getPresenter();
}
