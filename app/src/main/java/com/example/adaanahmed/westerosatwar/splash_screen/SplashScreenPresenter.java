package com.example.adaanahmed.westerosatwar.splash_screen;

import android.support.annotation.NonNull;

import com.example.adaanahmed.westerosatwar.dbUtil.models.Battle;
import com.example.adaanahmed.westerosatwar.dbUtil.models.King;
import com.example.adaanahmed.westerosatwar.network.ResponseModel;
import com.example.adaanahmed.westerosatwar.network.ServiceGenerator;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.HomeScreenRouterModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class SplashScreenPresenter implements SplashScreenContract.Presenter, Callback<ArrayList<ResponseModel>> {

    private WeakReference<SplashScreenContract.View> view;
    private boolean wasLoadSuccessful;
    long count1, count2;

    SplashScreenPresenter(@NonNull SplashScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    private void fetchData() {
        ServiceGenerator.GotClient client = ServiceGenerator.createService(ServiceGenerator.GotClient.class);
        Call<ArrayList<ResponseModel>> apiCall = client.battles();
        apiCall.enqueue(this);
    }

    @Override
    public void navigateToHomeScreen() {
        HomeScreenRouterModel model = new HomeScreenRouterModel(wasLoadSuccessful);
        TwRouter.startHomeScreenActivity(view.get().getContext(), model);
    }

    @Override
    public void start() {
        fetchData();
    }

    @Override
    public void stop() {
    }

    @Override
    public void onResponse(Response<ArrayList<ResponseModel>> response, Retrofit retrofit) {
        if (null != response) {
            view.get().onDataFetchSuccess();
            storeDataInDb(response.body());
            wasLoadSuccessful = response.body().size() > 0;
        } else {
            view.get().onDataFetchFailure("response was null");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        view.get().onDataFetchFailure(t.toString());
    }

    private void storeDataInDb(@NonNull final ArrayList<ResponseModel> responseModels) {
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                for (ResponseModel response : responseModels) {
                    Battle battle = insertBattleDocument(response, realm);
                    insertUpdateKingDocument(response, battle, realm);
                }

                count1 = realm.where(King.class).count();
                count2 = realm.where(Battle.class).count();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                view.get().onDataStoreSuccess();
                view.get().onDataStoreFailure(" kings -> " + count1 + " battles -> " + count2);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                view.get().onDataStoreFailure(error.toString());
            }
        });
    }

    private void insertUpdateKingDocument(ResponseModel response, Battle battle, Realm realm) {
        King king = realm.where(King.class).equalTo("name", response.getAttackerKing()).findFirst();
        if (king == null) {
            king = realm.createObject(King.class, response.getAttackerKing());
        }

        king.setStrength("none");
        king.setBattleType("none");

        if (response.getAttackerOutcome().equalsIgnoreCase("win")) {
            king.getBattlesWon().add(battle);
        } else {
            king.getBattlesLost().add(battle);
        }

    }

    private Battle insertBattleDocument(@NonNull final ResponseModel response, Realm realm) {

        Battle battle = realm.where(Battle.class).equalTo("name", response.getName()).findFirst();
        if (null != battle) {
            return battle;
        }

        battle = realm.createObject(Battle.class, response.getName());
        battle.setYear(response.getYear());
        battle.setAttackerKing(response.getAttackerKing());
        battle.setDefenderKing(response.getDefenderKing());
        battle.setBattleType(response.getBattleType());
        battle.setRegion(response.getRegion());
        return battle;
    }
}
