package cz.antelli.assistant.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepan on 27.08.2017.
 */

public class Answer implements Parcelable {

    private List<AnswerItem> items = new ArrayList<>();
    private List<Tip> tips;
    private boolean autoListen;

    public Answer() {
    }

    public Answer(AnswerItem item){
        getItems().add(item);
    }

    public Answer(List<AnswerItem> items){
        getItems().addAll(items);
    }

    public Answer addItem(AnswerItem item){
        getItems().add(item);
        return this;
    }

    public void addItems(List<AnswerItem> item){
        getItems().addAll(item);
    }

    public List<String> getToSpeak(){
        List<String> result = new ArrayList<>();
        for (AnswerItem item : getItems()) {
            if (item.getSpeech()!=null) {
                result.addAll(item.getSpeech());
            }
        }
        return result;
    }

    public List<AnswerItem> getItems() {
        return items;
    }

    public boolean hasAutoListen() {
        return autoListen;
    }

    public Answer setAutoListen(boolean autoListen) {
        this.autoListen = autoListen;
        return this;
    }

    public void addTip(Tip tip){
        if (tips == null) {
            tips = new ArrayList<>();
        }
        tips.add(tip);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.items);
        dest.writeTypedList(this.tips);
        dest.writeByte(this.autoListen ? (byte) 1 : (byte) 0);
    }

    protected Answer(Parcel in) {
        this.items = in.createTypedArrayList(AnswerItem.CREATOR);
        this.tips = in.createTypedArrayList(Tip.CREATOR);
        this.autoListen = in.readByte() != 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
