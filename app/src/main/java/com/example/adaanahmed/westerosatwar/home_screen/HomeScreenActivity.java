package com.example.adaanahmed.westerosatwar.home_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.base.BaseActivity;
import com.example.adaanahmed.westerosatwar.base.BasePresenter;
import com.example.adaanahmed.westerosatwar.dbUtil.models.King;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.HomeScreenRouterModel;

import java.util.ArrayList;

public class HomeScreenActivity extends BaseActivity implements HomeScreenContract.View,
        View.OnClickListener, HomeScreenListAdapter.HomeScreenListingCallbacks {

    private HomeScreenRouterModel model;
    private HomeScreenContract.Presenter presenter;
    private RecyclerView recyclerView;
    private ImageView searchButton, filterButton, homeButton;
    private HomeScreenListAdapter adapter;
    private View divider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = (RecyclerView) findViewById(R.id.home_screen_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_screen_toolbar);
        searchButton = (ImageView) toolbar.findViewById(R.id.toolbar_search);
        filterButton = (ImageView) toolbar.findViewById(R.id.toolbar_filter);
        homeButton = (ImageView) toolbar.findViewById(R.id.toolbar_home);
        divider = findViewById(R.id.home_screen_divider);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null && presenter.getView() == null) {
            presenter.setView(this);
        }
    }

    private void initViews() {
        presenter = new HomeScreenPresenter(this);
        searchButton.setOnClickListener(this);
        filterButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        adapter = new HomeScreenListAdapter(this, presenter.getTotalCount());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findFirstCompletelyVisibleItemPosition() > 0) {
                    divider.setVisibility(View.VISIBLE);
                } else {
                    divider.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void handleIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(TwRouter.KEY_DATA) && null != intent.getParcelableExtra(TwRouter.KEY_DATA)) {
            model = intent.getParcelableExtra(TwRouter.KEY_DATA);
        }

        if (model != null && !model.isWasLoadSuccessful()) {
            Log.d(this.getClass().getName(), "models not loaded successfully");
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
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_home:
                if (null != recyclerView) {
                    recyclerView.smoothScrollToPosition(0);
                }
                break;
            case R.id.toolbar_search:
                if (null != presenter) {
                    presenter.startSearchActivity();
                }
                break;
            case R.id.toolbar_filter:
                if (null != presenter) {
                    presenter.startFilterFragment();
                }
                break;
        }
    }

    @Override
    public void onKingsItemClick(King king) {
        if (null != presenter)
            presenter.startKingProfileActivity(king);
    }

    @Override
    public void loadMore() {
        if (null != presenter)
            presenter.fetchData();
    }

    @Override
    public void updateList(ArrayList<King> kings, boolean isComplete) {
        adapter.addData(kings);
        if (isComplete) {
            adapter.removeFooter();
        } else {
            adapter.addFooter();
        }
    }
}
