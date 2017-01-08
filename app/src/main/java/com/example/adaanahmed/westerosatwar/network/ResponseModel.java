
package com.example.adaanahmed.westerosatwar.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ResponseModel implements Parcelable{

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

    protected ResponseModel(Parcel in) {
        name = in.readString();
        year = in.readInt();
        battleNumber = in.readInt();
        attackerKing = in.readString();
        defenderKing = in.readString();
        attacker1 = in.readString();
        attacker2 = in.readString();
        attacker3 = in.readString();
        attacker4 = in.readString();
        defender1 = in.readString();
        defender2 = in.readString();
        defender3 = in.readString();
        defender4 = in.readString();
        attackerOutcome = in.readString();
        battleType = in.readString();
        majorDeath = in.readInt();
        majorCapture = in.readInt();
        attackerSize = in.readString();
        defenderSize = in.readString();
        attackerCommander = in.readString();
        defenderCommander = in.readString();
        summer = in.readString();
        location = in.readString();
        region = in.readString();
        note = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(year);
        dest.writeInt(battleNumber);
        dest.writeString(attackerKing);
        dest.writeString(defenderKing);
        dest.writeString(attacker1);
        dest.writeString(attacker2);
        dest.writeString(attacker3);
        dest.writeString(attacker4);
        dest.writeString(defender1);
        dest.writeString(defender2);
        dest.writeString(defender3);
        dest.writeString(defender4);
        dest.writeString(attackerOutcome);
        dest.writeString(battleType);
        dest.writeInt(majorDeath);
        dest.writeInt(majorCapture);
        dest.writeString(attackerSize);
        dest.writeString(defenderSize);
        dest.writeString(attackerCommander);
        dest.writeString(defenderCommander);
        dest.writeString(summer);
        dest.writeString(location);
        dest.writeString(region);
        dest.writeString(note);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseModel> CREATOR = new Creator<ResponseModel>() {
        @Override
        public ResponseModel createFromParcel(Parcel in) {
            return new ResponseModel(in);
        }

        @Override
        public ResponseModel[] newArray(int size) {
            return new ResponseModel[size];
        }
    };

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
