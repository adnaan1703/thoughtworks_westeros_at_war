package com.example.adaanahmed.westerosatwar.router;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.adaanahmed.westerosatwar.home_screen.HomeScreenActivity;
import com.example.adaanahmed.westerosatwar.router.models.HomeScreenRouterModel;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class TwRouter {

    public static final String KEY_DATA = "key_data";

    public static void startHomeScreenActivity(@NonNull Context context, @Nullable HomeScreenRouterModel model) {
        Intent intent = new Intent(context, HomeScreenActivity.class);
        intent.putExtra(KEY_DATA, model);
        context.startActivity(intent);
    }
}
