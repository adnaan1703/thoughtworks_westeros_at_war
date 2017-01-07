package com.example.adaanahmed.westerosatwar.home_screen;

import android.content.Intent;
import android.os.Bundle;

import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.base.BaseActivity;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.HomeScreenRouterModel;

public class HomeScreenActivity extends BaseActivity {

    private HomeScreenRouterModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    @Override
    protected void handleIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra(TwRouter.KEY_DATA) && null != intent.getParcelableExtra(TwRouter.KEY_DATA)) {
            model = intent.getParcelableExtra(TwRouter.KEY_DATA);
        }
    }
}
