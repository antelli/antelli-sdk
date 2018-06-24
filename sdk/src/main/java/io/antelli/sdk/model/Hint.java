package io.antelli.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */

public class Hint implements Parcelable {

    private String title;
    private Command command;

    public Hint() {
    }

    public Hint(String title) {
        this.title = title;
    }

    public Hint(String title, Command command) {
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

    protected Hint(Parcel in) {
        this.title = in.readString();
        this.command = in.readParcelable(Command.class.getClassLoader());
    }

    public static final Creator<Hint> CREATOR = new Creator<Hint>() {
        @Override
        public Hint createFromParcel(Parcel source) {
            return new Hint(source);
        }

        @Override
        public Hint[] newArray(int size) {
            return new Hint[size];
        }
    };
}
