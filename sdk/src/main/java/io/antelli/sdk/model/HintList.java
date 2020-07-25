package io.antelli.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Handcrafted by Štěpán Šonský on 02.07.2018.
 */

class HintList implements Parcelable {

    private List<Hint> hints;

    public HintList(){
        hints = new ArrayList();
    }

    public HintList(List<Hint> hints) {
        this.hints = hints;
    }

    public HintList add(Hint hint) {
        this.hints.add(hint);
        return this;
    }

    public HintList addAll(List<Hint> hint) {
        this.hints.addAll(hint);
        return this;
    }

    public List<Hint> getHints() {
        return hints;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.hints);
    }

    protected HintList(Parcel in) {
        this.hints = in.createTypedArrayList(Hint.CREATOR);
    }

    public static final Creator<HintList> CREATOR = new Creator<HintList>() {
        @Override
        public HintList createFromParcel(Parcel source) {
            return new HintList(source);
        }

        @Override
        public HintList[] newArray(int size) {
            return new HintList[size];
        }
    };
}
