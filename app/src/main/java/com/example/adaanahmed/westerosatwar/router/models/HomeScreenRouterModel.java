package com.example.adaanahmed.westerosatwar.router.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.adaanahmed.westerosatwar.network.ResponseModel;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class HomeScreenRouterModel implements Parcelable {

    private boolean wasLoadSuccessful;

    public HomeScreenRouterModel(boolean wasLoadSuccessful) {
        this.wasLoadSuccessful = wasLoadSuccessful;
    }

    protected HomeScreenRouterModel(Parcel in) {
        wasLoadSuccessful = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (wasLoadSuccessful ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
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

    public boolean isWasLoadSuccessful() {
        return wasLoadSuccessful;
    }
}
