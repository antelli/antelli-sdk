package cz.antelli.assistant.sdk;

import cz.antelli.antelliassistantsdk.BuildConfig;

/**
 * Created by stepan on 29.08.2017.
 */

public class AntelliSDK {

    public static int[] getApiVersion(){
        return new int[]{BuildConfig.VERSION_MAJOR, BuildConfig.VERSION_MINOR, BuildConfig.VERSION_PATCH};
    }

    public static String getApiVersionString(){
        return BuildConfig.VERSION_MAJOR + "." + BuildConfig.VERSION_MINOR + "." + BuildConfig.VERSION_PATCH;
    }
}
