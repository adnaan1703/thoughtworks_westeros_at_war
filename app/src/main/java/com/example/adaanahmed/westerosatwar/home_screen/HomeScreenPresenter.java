package com.example.adaanahmed.westerosatwar.home_screen;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.adaanahmed.westerosatwar.dbUtil.models.King;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.ProfileScreenRouterModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

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
            fetchData();
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
    public void setView(HomeScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public HomeScreenContract.View getView() {
        return view.get();
    }

    @Override
    public void fetchData() {
        ArrayList<King> data = new ArrayList<>();
        results = results.sort("rating", Sort.DESCENDING);
        for (int position = counter; position < counter + ROW_ITEMS_COUNT && position < results.size(); position++) {
            data.add(results.get(position));
        }

        if (null != view.get()) {
            if (counter + ROW_ITEMS_COUNT >= results.size()) {
                counter = results.size();
                view.get().updateList(data, true);
            } else {
                view.get().updateList(data, false);
            }
        }
    }

    @Override
    public void startSearchActivity() {
        if (view.get() != null) {
            TwRouter.startSearchScreenActivity(view.get().getContext());
        }
    }

    @Override
    public void startFilterFragment() {
        if (view.get() != null) {
            Toast.makeText(view.get().getContext().getApplicationContext(), "Sorry couldn't add the filter" +
                    " feature due to lack of time.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void startKingProfileActivity(@NonNull King king) {
        if (null != view.get()) {
            ProfileScreenRouterModel model = new ProfileScreenRouterModel(king.getName());
            TwRouter.startProfileScreenActivity(view.get().getContext(), model);
        }
    }

    @Override
    public int getTotalCount() {
        return (int) Realm.getDefaultInstance().where(King.class).count();
    }
}
