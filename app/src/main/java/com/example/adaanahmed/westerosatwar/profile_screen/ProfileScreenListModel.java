package com.example.adaanahmed.westerosatwar.profile_screen;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class ProfileScreenListModel {

    static final int HEADER = 0;
    static final int BATTLE_WON_HEADER = 1;
    static final int BATTLE_WON_CONTENT = 2;
    static final int BATTLE_LOST_HEADER = 3;
    static final int BATTLE_LOST_CONTENT = 4;

    private int type;
    private int kingId;
    private String name;
    private int year;
    private String kingName;
    private long rating;
    private String battleStrength;
    private String strength;
    private int count;

    public ProfileScreenListModel() {
    }

    public int getKingId() {
        return kingId;
    }

    public void setKingId(int kingId) {
        this.kingId = kingId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKingName() {
        return kingName;
    }

    public void setKingName(String kingName) {
        this.kingName = kingName;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getBattleStrength() {
        return battleStrength;
    }

    public void setBattleStrength(String battleStrength) {
        this.battleStrength = battleStrength;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
