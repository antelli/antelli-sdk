package io.antelli.sdk.model;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Handcrafted by Štěpán Šonský on 11.11.2017.
 */

public class Command implements Parcelable{

    private static final String PARAM_ACTION = "ACTION";
    private static final String PARAM_INTENT = "INTENT";
    private static final String ACTION_INTENT = "intent";

    private Bundle params = new Bundle();

    public Command(String action) {
        params.putString(PARAM_ACTION, action);
    }

    public Command(Intent intent) {
        params.putString(PARAM_ACTION, ACTION_INTENT);
        params.putParcelable(PARAM_INTENT, intent);
    }

    public String getAction(){
        return params.getString(PARAM_ACTION);
    }

    public Command putBoolean(String name, boolean value){
        params.putBoolean(name, value);
        return this;
    }

    public Command putInt(String name, int value){
        params.putInt(name, value);
        return this;
    }

    public Command putDouble(String name, double value){
        params.putDouble(name, value);
        return this;
    }

    public Command putLong(String name, long value){
        params.putLong(name, value);
        return this;
    }

    public Command putString(String name, String value){
        params.putString(name, value);
        return this;
    }

    public Command putParcelable(String name, Parcelable value){
        params.putParcelable(name, value);
        return this;
    }

    public boolean getBoolean(String name){
        return params.getBoolean(name, false);
    }

    public int getInt(String name){
        return params.getInt(name, 0);
    }

    public long getLong(String name){
        return params.getLong(name, 0);
    }

    public double getDouble(String name){
        return params.getDouble(name, 0);
    }

    public String getString(String name){
        return params.getString(name);
    }

    public String getParcelable(String name){
        return params.getParcelable(name);
    }

    public Intent getIntent(){
        return params.getParcelable(PARAM_INTENT);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(this.params);
    }

    protected Command(Parcel in) {
        this.params = in.readBundle(getClass().getClassLoader());
    }

    public static final Creator<Command> CREATOR = new Creator<Command>() {
        @Override
        public Command createFromParcel(Parcel source) {
            return new Command(source);
        }

        @Override
        public Command[] newArray(int size) {
            return new Command[size];
        }
    };
}
