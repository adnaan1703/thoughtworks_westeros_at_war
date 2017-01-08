package com.example.adaanahmed.westerosatwar.dbUtil.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class King extends RealmObject {

    private long rating = 400;
    @PrimaryKey private String name;
    private String strength;
    private String battleType;
    private RealmList<Battle> battlesWon = new RealmList<>();
    private RealmList<Battle> battlesLost = new RealmList<>();

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getBattleType() {
        return battleType;
    }

    public void setBattleType(String battleType) {
        this.battleType = battleType;
    }

    public RealmList<Battle> getBattlesWon() {
        return battlesWon;
    }

    public void setBattlesWon(RealmList<Battle> battlesWon) {
        this.battlesWon = battlesWon;
    }

    public RealmList<Battle> getBattlesLost() {
        return battlesLost;
    }

    public void setBattlesLost(RealmList<Battle> battlesLost) {
        this.battlesLost = battlesLost;
    }
}
