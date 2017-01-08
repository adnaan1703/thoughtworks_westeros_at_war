package com.example.adaanahmed.westerosatwar.utils;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class IdGenerator {

    private static int counter = 0;

    public static int generateId() {
        return counter = (++counter) % 50;
    }
}
