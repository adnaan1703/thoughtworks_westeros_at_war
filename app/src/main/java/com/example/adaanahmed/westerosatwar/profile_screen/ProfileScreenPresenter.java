package com.example.adaanahmed.westerosatwar.profile_screen;

import android.support.annotation.NonNull;

import com.example.adaanahmed.westerosatwar.dbUtil.models.Battle;
import com.example.adaanahmed.westerosatwar.dbUtil.models.King;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.realm.Realm;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class ProfileScreenPresenter implements ProfileScreenContract.Presenter {


    private WeakReference<ProfileScreenContract.View> view;
    private String kingName;

    ProfileScreenPresenter(@NonNull ProfileScreenContract.View view, @NonNull String kingName) {
        this.view = new WeakReference<>(view);
        this.kingName = kingName;
    }

    @Override
    public void start() {
        King king = Realm.getDefaultInstance()
                .where(King.class)
                .equalTo("name", kingName)
                .findFirst();
        if (null != view.get())
            view.get().setData(generateDisplayModel(king));
    }

    private ArrayList<ProfileScreenListModel> generateDisplayModel(King king) {
        ArrayList<ProfileScreenListModel> models = new ArrayList<>();
        ProfileScreenListModel model = new ProfileScreenListModel();
        model.setType(ProfileScreenListModel.HEADER);
        model.setKingName(king.getName());
        model.setRating(king.getRating());
        model.setStrength(king.getStrength());
        model.setBattleStrength(king.getBattleType());
        models.add(model);

        model = new ProfileScreenListModel();
        model.setType(ProfileScreenListModel.BATTLE_WON_HEADER);
        model.setCount(king.getBattlesWon().size());
        models.add(model);

        for (int i = 0; i < king.getBattlesWon().size(); i++) {
            Battle battle = king.getBattlesWon().get(i);
            model = new ProfileScreenListModel();
            model.setType(ProfileScreenListModel.BATTLE_WON_CONTENT);
            model.setName(battle.getName());
            model.setYear((int) battle.getYear());
            models.add(model);
        }

        model = new ProfileScreenListModel();
        model.setType(ProfileScreenListModel.BATTLE_LOST_HEADER);
        model.setCount(king.getBattlesLost().size());
        models.add(model);

        for (int i = 0; i < king.getBattlesLost().size(); i++) {
            Battle battle = king.getBattlesLost().get(i);
            model = new ProfileScreenListModel();
            model.setType(ProfileScreenListModel.BATTLE_LOST_CONTENT);
            model.setName(battle.getName());
            model.setYear((int) battle.getYear());
            models.add(model);
        }

        return models;
    }

    @Override
    public void stop() {

    }

    @Override
    public void setView(ProfileScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public ProfileScreenContract.View getView() {
        return view.get();
    }
}
