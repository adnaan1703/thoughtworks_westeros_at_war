
package com.example.adaanahmed.westerosatwar.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

@SuppressWarnings("unused")
public class ResponseModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("battle_number")
    @Expose
    private int battleNumber;
    @SerializedName("attacker_king")
    @Expose
    private String attackerKing;
    @SerializedName("defender_king")
    @Expose
    private String defenderKing;
    @SerializedName("attacker_1")
    @Expose
    private String attacker1;
    @SerializedName("attacker_2")
    @Expose
    private String attacker2;
    @SerializedName("attacker_3")
    @Expose
    private String attacker3;
    @SerializedName("attacker_4")
    @Expose
    private String attacker4;
    @SerializedName("defender_1")
    @Expose
    private String defender1;
    @SerializedName("defender_2")
    @Expose
    private String defender2;
    @SerializedName("defender_3")
    @Expose
    private String defender3;
    @SerializedName("defender_4")
    @Expose
    private String defender4;
    @SerializedName("attacker_outcome")
    @Expose
    private String attackerOutcome;
    @SerializedName("battle_type")
    @Expose
    private String battleType;
    @SerializedName("major_death")
    @Expose
    private int majorDeath;
    @SerializedName("major_capture")
    @Expose
    private int majorCapture;
    @SerializedName("attacker_size")
    @Expose
    private String attackerSize;
    @SerializedName("defender_size")
    @Expose
    private String defenderSize;
    @SerializedName("attacker_commander")
    @Expose
    private String attackerCommander;
    @SerializedName("defender_commander")
    @Expose
    private String defenderCommander;
    @SerializedName("summer")
    @Expose
    private String summer;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("note")
    @Expose
    private String note;

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getBattleNumber() {
        return battleNumber;
    }

    public String getAttackerKing() {
        return attackerKing;
    }

    public String getDefenderKing() {
        return defenderKing;
    }

    public String getAttacker1() {
        return attacker1;
    }

    public String getAttacker2() {
        return attacker2;
    }

    public String getAttacker3() {
        return attacker3;
    }

    public String getAttacker4() {
        return attacker4;
    }

    public String getDefender1() {
        return defender1;
    }

    public String getDefender2() {
        return defender2;
    }

    public String getDefender3() {
        return defender3;
    }

    public String getDefender4() {
        return defender4;
    }

    public String getAttackerOutcome() {
        return attackerOutcome;
    }

    public String getBattleType() {
        return battleType;
    }

    public int getMajorDeath() {
        return majorDeath;
    }

    public int getMajorCapture() {
        return majorCapture;
    }

    public String getAttackerSize() {
        return attackerSize;
    }

    public String getDefenderSize() {
        return defenderSize;
    }

    public String getAttackerCommander() {
        return attackerCommander;
    }

    public String getDefenderCommander() {
        return defenderCommander;
    }

    public String getSummer() {
        return summer;
    }

    public String getLocation() {
        return location;
    }

    public String getRegion() {
        return region;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
