package com.example.adaanahmed.westerosatwar.dbUtil.models;

import io.realm.RealmObject;


/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class Battle extends RealmObject {

    private long year;
    private String name;
    private String attackerKing;
    private String defenderKing;
    private String battleType;
    private String region;

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttackerKing() {
        return attackerKing;
    }

    public void setAttackerKing(String attackerKing) {
        this.attackerKing = attackerKing;
    }

    public String getDefenderKing() {
        return defenderKing;
    }

    public void setDefenderKing(String defenderKing) {
        this.defenderKing = defenderKing;
    }

    public String getBattleType() {
        return battleType;
    }

    public void setBattleType(String battleType) {
        this.battleType = battleType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
