package com.example.adaanahmed.westerosatwar.search_screen;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class SearchModel {
    private String kingName;
    private long rating;
    private int id;

    public SearchModel(String kingName, long rating, int id) {
        this.kingName = kingName;
        this.rating = rating;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
