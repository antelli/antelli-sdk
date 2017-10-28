package cz.antelli.sdk.model;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by stepan on 27.08.2017.
 */

public class AnswerItem implements Parcelable {

    private static final String PARAM_TITLE = "TITLE";
    private static final String PARAM_SUBTITLE = "SUBTITLE";
    private static final String PARAM_TEXT = "TEXT";
    private static final String PARAM_IMAGE = "IMAGE";
    private static final String PARAM_IMAGE_SCALE_TYPE = "IMAGE_SCALE_TYPE";
    private static final String PARAM_COMMAND = "COMMAND";
    private static final String PARAM_SPEECH = "SPEECH";
    private static final String PARAM_TYPE = "TYPE";

    public static final int TYPE_CONVERSATION = 1;
    public static final int TYPE_CARD = 2;

    public static final int IMAGE_CROP = 1;
    public static final int IMAGE_FIT = 2;

    private Bundle params = new Bundle();

    public ArrayList<String> getSpeech() {
        return params.getStringArrayList(PARAM_SPEECH);
    }

    public AnswerItem speak(String text){
        ArrayList<String> speech = params.getStringArrayList(PARAM_SPEECH);
        if (speech == null){
            speech = new ArrayList<>();
        }
        speech.add(text);
        params.putStringArrayList(PARAM_SPEECH, speech);
        return this;
    }

    public AnswerItem() {
    }

    public String getText() {
        return params.getString(PARAM_TEXT);
    }

    public AnswerItem setText(String text) {
        params.putString(PARAM_TEXT, text);
        return this;
    }

    public String getTitle() {
        return params.getString(PARAM_TITLE);
    }

    public AnswerItem setTitle(String title) {
        params.putString(PARAM_TITLE, title);
        return this;
    }

    public String getSubtitle() {
        return params.getString(PARAM_SUBTITLE);
    }

    public AnswerItem setSubtitle(String subtitle) {
        params.putString(PARAM_SUBTITLE, subtitle);
        return this;
    }

    public String getImage() {
        return params.getString(PARAM_IMAGE);
    }

    public AnswerItem setImage(String image) {
        params.putString(PARAM_IMAGE, image);
        return this;
    }

    public int getType() {
        return params.getInt(PARAM_TYPE, TYPE_CONVERSATION);
    }

    public AnswerItem setType(int type) {
        params.putInt(PARAM_TYPE, type);
        return this;
    }

    public String getCommand() {
        return params.getString(PARAM_COMMAND);
    }

    public AnswerItem setCommand(String command) {
        params.putString(PARAM_COMMAND, command);
        return this;
    }

    public int getImageScaleType() {
        return params.getInt(PARAM_IMAGE_SCALE_TYPE, IMAGE_CROP);
    }

    public AnswerItem setImageScaleType(int imageScaleType) {
        params.putInt(PARAM_IMAGE_SCALE_TYPE, imageScaleType);
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(this.params);
    }

    protected AnswerItem(Parcel in) {
        this.params = in.readBundle();
    }

    public static final Creator<AnswerItem> CREATOR = new Creator<AnswerItem>() {
        @Override
        public AnswerItem createFromParcel(Parcel source) {
            return new AnswerItem(source);
        }

        @Override
        public AnswerItem[] newArray(int size) {
            return new AnswerItem[size];
        }
    };
}
