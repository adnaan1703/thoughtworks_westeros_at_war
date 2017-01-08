package com.example.adaanahmed.westerosatwar.router.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class ProfileScreenRouterModel implements Parcelable {

    private String kingName;

    public ProfileScreenRouterModel(String kingName) {
        this.kingName = kingName;
    }

    protected ProfileScreenRouterModel(Parcel in) {
        kingName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kingName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProfileScreenRouterModel> CREATOR = new Creator<ProfileScreenRouterModel>() {
        @Override
        public ProfileScreenRouterModel createFromParcel(Parcel in) {
            return new ProfileScreenRouterModel(in);
        }

        @Override
        public ProfileScreenRouterModel[] newArray(int size) {
            return new ProfileScreenRouterModel[size];
        }
    };

    public String getKingName() {
        return kingName;
    }

    public void setKingName(String kingName) {
        this.kingName = kingName;
    }
}
