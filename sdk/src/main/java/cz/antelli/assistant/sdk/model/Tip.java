package cz.antelli.assistant.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by stepan on 28.08.2017.
 */

public class Tip implements Parcelable {

    private String title;
    private String command;
    private String query;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.command);
        dest.writeString(this.query);
    }

    public Tip() {
    }

    protected Tip(Parcel in) {
        this.title = in.readString();
        this.command = in.readString();
        this.query = in.readString();
    }

    public static final Creator<Tip> CREATOR = new Creator<Tip>() {
        @Override
        public Tip createFromParcel(Parcel source) {
            return new Tip(source);
        }

        @Override
        public Tip[] newArray(int size) {
            return new Tip[size];
        }
    };
}
