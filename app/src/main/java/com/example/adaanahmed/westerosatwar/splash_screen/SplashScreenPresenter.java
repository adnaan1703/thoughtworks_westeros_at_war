package com.example.adaanahmed.westerosatwar.splash_screen;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.adaanahmed.westerosatwar.network.ResponseModel;
import com.example.adaanahmed.westerosatwar.network.ServiceGenerator;
import com.example.adaanahmed.westerosatwar.router.TwRouter;
import com.example.adaanahmed.westerosatwar.router.models.HomeScreenRouterModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class SplashScreenPresenter implements SplashScreenContract.Presenter, Callback<ArrayList<ResponseModel>>{

    private WeakReference<SplashScreenContract.View> view;

    SplashScreenPresenter(@NonNull SplashScreenContract.View view) {
        this.view = new WeakReference<>(view);
    }

    private void fetchData() {
        ServiceGenerator.GotClient client = ServiceGenerator.createService(ServiceGenerator.GotClient.class);
        Call<ArrayList<ResponseModel>> apiCall = client.battles();
        apiCall.enqueue(this);
    }

    @Override
    public void navigateToHomeScreen() {
        HomeScreenRouterModel model = new HomeScreenRouterModel("awesome");
        TwRouter.startHomeScreenActivity(view.get().getContext(), model);
    }

    @Override
    public void start() {
        fetchData();
    }

    @Override
    public void onResponse(Response<ArrayList<ResponseModel>> response, Retrofit retrofit) {
        view.get().setDataSuccess(response.body());
    }

    @Override
    public void onFailure(Throwable t) {
        view.get().setDataFailure(t.toString());
    }
}
