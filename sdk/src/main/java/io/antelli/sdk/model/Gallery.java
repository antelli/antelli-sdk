package io.antelli.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Handcrafted by Štěpán Šonský on 12.11.2017.
 */

public class Gallery implements Parcelable {

    private List<AnswerItem> items = new ArrayList<>();

    public Gallery() {
    }

    public Gallery addItem(AnswerItem item) {
        this.items.add(item);
        return this;
    }

    public Gallery addItems(List<AnswerItem> item) {
        this.items.addAll(item);
        return this;
    }

    public List<AnswerItem> getItems() {
        return items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.items);
    }

    protected Gallery(Parcel in) {
        this.items = in.createTypedArrayList(AnswerItem.CREATOR);
    }

    public static final Creator<Gallery> CREATOR = new Creator<Gallery>() {
        @Override
        public Gallery createFromParcel(Parcel source) {
            return new Gallery(source);
        }

        @Override
        public Gallery[] newArray(int size) {
            return new Gallery[size];
        }
    };
}
