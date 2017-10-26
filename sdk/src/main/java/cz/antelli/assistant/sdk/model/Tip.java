package cz.antelli.assistant.sdk.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by stepan on 28.08.2017.
 */

public class Tip implements Parcelable {

    private String title;
    private Uri command;
    private String query;

    public Tip() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getCommand() {
        return command;
    }

    public void setCommand(Uri command) {
        this.command = command;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getTitle());
        dest.writeParcelable(this.getCommand(), flags);
        dest.writeString(this.getQuery());
    }

    protected Tip(Parcel in) {
        this.setTitle(in.readString());
        this.setCommand((Uri) in.readParcelable(Uri.class.getClassLoader()));
        this.setQuery(in.readString());
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
