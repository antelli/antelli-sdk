package io.antelli.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */

public class Tip implements Parcelable {

    private String title;
    private Command command;

    public Tip() {
    }

    public Tip(String title) {
        this.title = title;
    }

    public Tip(String title, Command command) {
        this.title = title;
        this.command = command;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.command, flags);
    }

    protected Tip(Parcel in) {
        this.title = in.readString();
        this.command = in.readParcelable(Command.class.getClassLoader());
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
