package com.example.adaanahmed.westerosatwar.home_screen;

import android.support.annotation.NonNull;

import com.example.adaanahmed.westerosatwar.dbUtil.models.King;

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


class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private WeakReference<HomeScreenContract.View> view;
    private int counter = 0;
    private RealmResults<King> results;
    private RealmChangeListener<RealmResults<King>> callbacks = new RealmChangeListener<RealmResults<King>>() {
        @Override
        public void onChange(RealmResults<King> element) {
            results = element;
            fetchData(counter);
        }
    };


    HomeScreenPresenter(@NonNull HomeScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void start() {
        results = Realm.getDefaultInstance().where(King.class).findAllAsync();
        results.addChangeListener(callbacks);
    }

    @Override
    public void stop() {
        results.removeChangeListeners();
        view.clear();
    }

    @Override
    public void fetchData(int index) {
        ArrayList<King> data = new ArrayList<>();
        for (int position = index; position < index + ROW_ITEMS_COUNT && position < results.size(); position++) {
            data.add(results.get(position));
        }

        if (index + ROW_ITEMS_COUNT >= results.size()) {
            counter = results.size();
        }

        view.get().updateList(data, false);
    }

    @Override
    public void startSearchFragment() {

    }

    @Override
    public void startFilterFragment() {

    }

    @Override
    public void startKingProfileActivity(King king) {

    }
}
