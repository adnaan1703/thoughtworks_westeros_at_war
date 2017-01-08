package com.example.adaanahmed.westerosatwar.profile_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.UIWidget.ProximaTextView;
import com.example.adaanahmed.westerosatwar.base.BaseActivity;
import com.example.adaanahmed.westerosatwar.base.BasePresenter;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.ProfileScreenRouterModel;

import java.util.ArrayList;

public class ProfileScreenActivity extends BaseActivity implements ProfileScreenContract.View {

    private ProfileScreenContract.Presenter presenter;
    private ProfileScreenAdapter adapter;
    private ProfileScreenRouterModel model;
    private ProximaTextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.profile_screen_toolbar);
        setToolbar(toolbar);
        toolbarTitle = (ProximaTextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.INVISIBLE);
        adapter = new ProfileScreenAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.profile_Screen_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findFirstCompletelyVisibleItemPosition() > 0) {
                    toolbarTitle.setVisibility(View.VISIBLE);
                } else {
                    toolbarTitle.setVisibility(View.INVISIBLE);
                }

            }
        });
        if (model != null)
            presenter = new ProfileScreenPresenter(this, model.getKingName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null && presenter.getView() == null) {
            presenter.setView(this);
        }
    }

    @Override
    protected void handleIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(TwRouter.KEY_DATA) && intent.getParcelableExtra(TwRouter.KEY_DATA) != null) {
            model = intent.getParcelableExtra(TwRouter.KEY_DATA);
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void resetPresenter() {
        presenter = null;
    }

    @Override
    public void setData(ArrayList<ProfileScreenListModel> profileScreenListModels) {
        adapter.swapData(profileScreenListModels);
    }

    @Override
    public void setTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
