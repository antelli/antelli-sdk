package io.antelli.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Handcrafted by Štěpán Šonský on 12.11.2017.
 */

class AnswerItemList implements Parcelable {

    private List<AnswerItem> items;

    public AnswerItemList(List<AnswerItem> items) {
        this.items = items;
    }

    public AnswerItemList add(AnswerItem item) {
        this.items.add(item);
        return this;
    }

    public AnswerItemList addAll(List<AnswerItem> item) {
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

    protected AnswerItemList(Parcel in) {
        this.items = in.createTypedArrayList(AnswerItem.CREATOR);
    }

    public static final Creator<AnswerItemList> CREATOR = new Creator<AnswerItemList>() {
        @Override
        public AnswerItemList createFromParcel(Parcel source) {
            return new AnswerItemList(source);
        }

        @Override
        public AnswerItemList[] newArray(int size) {
            return new AnswerItemList[size];
        }
    };
}
