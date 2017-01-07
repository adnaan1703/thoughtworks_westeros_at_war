package com.example.adaanahmed.westerosatwar.router.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class HomeScreenRouterModel implements Parcelable {

    String data;

    public HomeScreenRouterModel(String data) {
        this.data = data;
    }

    protected HomeScreenRouterModel(Parcel in) {
        data = in.readString();
    }

    public static final Creator<HomeScreenRouterModel> CREATOR = new Creator<HomeScreenRouterModel>() {
        @Override
        public HomeScreenRouterModel createFromParcel(Parcel in) {
            return new HomeScreenRouterModel(in);
        }

        @Override
        public HomeScreenRouterModel[] newArray(int size) {
            return new HomeScreenRouterModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(data);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
