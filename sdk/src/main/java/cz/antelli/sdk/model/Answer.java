package cz.antelli.sdk.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepan on 27.08.2017.
 */

public class Answer implements Parcelable {

    private static final String PARAM_AUTO_LISTEN = "AUTO_LISTEN";

    private List<AnswerItem> items = new ArrayList<>();
    private List<Tip> tips;
    private Bundle params = new Bundle();
    //private boolean autoListen;

    public Answer() {
    }

    public Answer(String text) {
        addItem(new AnswerItem().setText(text).speak(text));
    }

    /*public Answer(AnswerItem item){
        getItems().add(item);
    }

    public Answer(List<AnswerItem> items){
        getItems().addAll(items);
    }*/

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

    public boolean isAutoListen() {
        return params.getBoolean(PARAM_AUTO_LISTEN, false);
    }

    public Answer setAutoListen(boolean autoListen) {
        params.putBoolean(PARAM_AUTO_LISTEN, autoListen);
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
        dest.writeBundle(this.params);
    }

    protected Answer(Parcel in) {
        this.items = in.createTypedArrayList(AnswerItem.CREATOR);
        this.tips = in.createTypedArrayList(Tip.CREATOR);
        this.params = in.readBundle();
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
