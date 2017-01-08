package com.example.adaanahmed.westerosatwar.router;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.adaanahmed.westerosatwar.home_screen.HomeScreenActivity;
import com.example.adaanahmed.westerosatwar.profile_screen.ProfileScreenActivity;
import com.example.adaanahmed.westerosatwar.router.models.HomeScreenRouterModel;
import com.example.adaanahmed.westerosatwar.router.models.ProfileScreenRouterModel;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class TwRouter {

    public static final String KEY_DATA = "key_data";

    public static void startHomeScreenActivity(@NonNull Context context, @NonNull HomeScreenRouterModel model) {
        Intent intent = new Intent(context, HomeScreenActivity.class);
        intent.putExtra(KEY_DATA, model);
        context.startActivity(intent);
    }

    public static void startProfileScreenActivity(@NonNull Context context, @NonNull ProfileScreenRouterModel model) {
        Intent intent = new Intent(context, ProfileScreenActivity.class);
        intent.putExtra(KEY_DATA, model);
        context.startActivity(intent);
    }

}
