package com.example.adaanahmed.westerosatwar.splash_screen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;

import com.example.adaanahmed.westerosatwar.dbUtil.models.Battle;
import com.example.adaanahmed.westerosatwar.dbUtil.models.King;
import com.example.adaanahmed.westerosatwar.network.ResponseModel;
import com.example.adaanahmed.westerosatwar.network.ServiceGenerator;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.HomeScreenRouterModel;
import com.example.adaanahmed.westerosatwar.utils.IdGenerator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
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
        if (null != view.get())
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
    public void setView(SplashScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public SplashScreenContract.View getView() {
        return view.get();
    }

    @Override
    public void onResponse(Response<ArrayList<ResponseModel>> response, Retrofit retrofit) {
        if (view.get() == null)
            return;

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
        if (null != view.get())
            view.get().onDataFetchFailure(t.toString());
    }

    private void storeDataInDb(@NonNull final ArrayList<ResponseModel> responseModels) {
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                for (ResponseModel response : responseModels) {

                    if (realm.where(Battle.class).equalTo("name", response.getName()).count() > 0)
                        continue;

                    Battle battle = insertBattleDocument(response, realm);
                    insertUpdateKingDocument(response, battle, realm);
                    updateBattleDatasOfKings(realm);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (null != view.get())
                    view.get().onDataStoreSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (null != view.get())
                    view.get().onDataStoreFailure(error.toString());
            }
        });
    }

    private void updateBattleDatasOfKings(Realm realm) {
        RealmResults<King> results = realm.where(King.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            King king = results.get(i);
            king.setId(IdGenerator.generateId());
            int attackCount = 0, defenceCount = 0;
            String battleType = "none";
            int maxBattleTypeCount = 0;


            for (int j = 0; j < king.getBattlesWon().size(); j++) {
                Battle battle = king.getBattlesWon().get(j);
                if (battle.getAttackerKing().equalsIgnoreCase(king.getName())) {
                    attackCount++;
                } else {
                    defenceCount++;
                }

                int battleTypeCount = 0;
                for (int k = j + 1; k < king.getBattlesWon().size(); k++) {
                    if (king.getBattlesWon().get(k).getBattleType().equalsIgnoreCase(battle.getBattleType()))
                        battleTypeCount++;
                }

                if (battleTypeCount > maxBattleTypeCount) {
                    battleType = battle.getBattleType();
                    maxBattleTypeCount = battleTypeCount;
                }
            }

            if (attackCount > defenceCount) {
                king.setStrength("Attack");
            } else if (attackCount < defenceCount) {
                king.setStrength("Defence");
            } else {
                king.setStrength("Balanced");
            }
            king.setBattleType(battleType);
        }
    }

    private void insertUpdateKingDocument(ResponseModel response, Battle battle, Realm realm) {

        boolean shouldComputeRating1 = true, shouldComputeRating2 = true;

        King attackingKing = realm.where(King.class).equalTo("name", response.getAttackerKing()).findFirst();
        if (attackingKing == null) {
            attackingKing = realm.createObject(King.class, response.getAttackerKing());
            shouldComputeRating1 = false;
        }


        attackingKing.setStrength("none");
        attackingKing.setBattleType("none");

        if (response.getAttackerOutcome().equalsIgnoreCase("win")) {
            attackingKing.getBattlesWon().add(battle);
        } else {
            attackingKing.getBattlesLost().add(battle);
        }

        King defendingKing = realm.where(King.class).equalTo("name", response.getDefenderKing()).findFirst();
        if (defendingKing == null) {
            defendingKing = realm.createObject(King.class, response.getDefenderKing());
            shouldComputeRating2 = false;
        }

        defendingKing.setStrength("none");
        defendingKing.setBattleType("none");

        if (response.getAttackerOutcome().equalsIgnoreCase("win")) {
            defendingKing.getBattlesLost().add(battle);
        } else {
            defendingKing.getBattlesWon().add(battle);
        }

        double flag = 0.5;
        if (battle.getAttackerOutcome().equalsIgnoreCase("win"))
            flag = 1;
        else if (battle.getAttackerOutcome().equalsIgnoreCase("loss"))
            flag = 0;

        long r1 = attackingKing.getRating();
        long r2 = defendingKing.getRating();

        if (shouldComputeRating1)
            attackingKing.setRating(computeRating(r1, r2, flag));

        if (shouldComputeRating2)
            defendingKing.setRating(computeRating(r2, r1, 1.0 - flag));
    }

    private long computeRating(long r1, long r2, double s1) {
        double k = 32.0;

        double R1 = Math.pow(10, r1 / 400);
        double R2 = Math.pow(10, r2 / 400);

        double e1 = R1 / (R1 + R2);

        double nr1 = r1 + k * (s1 - e1);
        return (long) Math.abs(nr1);

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
        battle.setAttackerOutcome(response.getAttackerOutcome());
        return battle;
    }
}
