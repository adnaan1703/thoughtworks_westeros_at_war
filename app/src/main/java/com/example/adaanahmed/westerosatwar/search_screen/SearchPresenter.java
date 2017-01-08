package com.example.adaanahmed.westerosatwar.search_screen;

import android.support.annotation.NonNull;

import com.example.adaanahmed.westerosatwar.dbUtil.models.King;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.ProfileScreenRouterModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class SearchPresenter implements SearchScreenContract.Presenter {


    private WeakReference<SearchScreenContract.View> view;
    private RealmResults<King> results;
    private RealmChangeListener<RealmResults<King>> callbacks = new RealmChangeListener<RealmResults<King>>() {
        @Override
        public void onChange(RealmResults<King> element) {
            results = element;
            generateDisplayModel();
        }
    };

    private void generateDisplayModel() {
        ArrayList<SearchModel> searchModels = new ArrayList<>();
        for (King king : results) {
            searchModels.add(new SearchModel(king.getName(), king.getRating(), king.getId()));
        }

        if (view.get() != null) {
            view.get().setData(searchModels);
        }
    }

    public SearchPresenter(@NonNull SearchScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void startKingProfileActivity(String kingName) {
        if (view.get() != null) {
            ProfileScreenRouterModel model = new ProfileScreenRouterModel(kingName);
            TwRouter.startProfileScreenActivity(view.get().getContext(), model);
        }
    }

    @Override
    public void fetchingNames(String str) {
        results = Realm.getDefaultInstance()
                .where(King.class)
                .contains("name", str)
                .findAllAsync();
        results.addChangeListener(callbacks);
    }

    @Override
    public void start() {
        fetchingNames("");
    }

    @Override
    public void stop() {
        results.removeChangeListeners();
    }

    @Override
    public void setView(SearchScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public SearchScreenContract.View getView() {
        return view.get();
    }
}
