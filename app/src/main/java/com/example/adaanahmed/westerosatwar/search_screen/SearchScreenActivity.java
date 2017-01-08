package com.example.adaanahmed.westerosatwar.search_screen;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.UIWidget.ProximaEditText;
import com.example.adaanahmed.westerosatwar.base.BaseActivity;
import com.example.adaanahmed.westerosatwar.base.BasePresenter;

import java.util.ArrayList;

public class SearchScreenActivity extends BaseActivity implements SearchScreenContract.View,
        SearchScreenAdapter.OnSearchItemSelectListener {

    private SearchScreenContract.Presenter presenter;
    private ProximaEditText editText;
    private ImageView searchButton;
    private SearchScreenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        editText = (ProximaEditText) findViewById(R.id.search_bar);
        searchButton = (ImageView) findViewById(R.id.search_button);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_list);
        presenter = new SearchPresenter(this);
        adapter = new SearchScreenAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        initView();
    }

    private void initView() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.fetchingNames(editText.getText().toString());
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.fetchingNames(editable.toString());
            }
        });


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
        // no intent...
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
    public void setData(ArrayList<SearchModel> searchModels) {
        adapter.swapData(searchModels);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onSearchItemSelect(String kingName) {
        presenter.startKingProfileActivity(kingName);
    }
}
