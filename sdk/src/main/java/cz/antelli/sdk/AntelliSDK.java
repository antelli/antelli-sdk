package cz.antelli.sdk;

/**
 * Created by stepan on 29.08.2017.
 */

public class AntelliSDK {

    public static int[] getApiVersion(){
        return new int[]{BuildConfig.VERSION_MAJOR, BuildConfig.VERSION_MINOR, BuildConfig.VERSION_PATCH};
    }

}
