package io.antelli.sdk.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Handcrafted by Štěpán Šonský on 27.08.2017.
 */

public class AnswerItem implements Parcelable {

    private static final String PARAM_TITLE = "TITLE";
    private static final String PARAM_SUBTITLE = "SUBTITLE";
    private static final String PARAM_TEXT = "TEXT";
    private static final String PARAM_LARGE_TEXT = "LARGE_TEXT";
    private static final String PARAM_IMAGE = "IMAGE";
    private static final String PARAM_SECONDARY_IMAGE = "SECONDARY_IMAGE";
    private static final String PARAM_IMAGE_SCALE_TYPE = "IMAGE_SCALE_TYPE";
    private static final String PARAM_COMMAND = "COMMAND";
    private static final String PARAM_SPEECH = "SPEECH";
    private static final String PARAM_ITEMS = "ITEMS";
    private static final String PARAM_HINTS = "HINTS";
    private static final String PARAM_TYPE = "TYPE";

    public static final int TYPE_CONVERSATION = 0;
    public static final int TYPE_CARD = 1;
    public static final int TYPE_IMAGE = 2;
    public static final int TYPE_CAROUSEL_SMALL = 3;
    public static final int TYPE_CAROUSEL_MEDIUM = 4;
    public static final int TYPE_CAROUSEL_LARGE = 5;

    private Bundle params = new Bundle();

    public AnswerItem() {
    }

    public String getText() {
        return params.getString(PARAM_TEXT);
    }

    public AnswerItem setText(String text) {
        params.putString(PARAM_TEXT, text);
        return this;
    }

    public String getLargeText() {
        return params.getString(PARAM_LARGE_TEXT);
    }

    public AnswerItem setLargeText(String text) {
        params.putString(PARAM_LARGE_TEXT, text);
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

    public String getSecondaryImage() {
        return params.getString(PARAM_SECONDARY_IMAGE);
    }

    public AnswerItem setSecondaryImage(String secondaryImage) {
        params.putString(PARAM_SECONDARY_IMAGE, secondaryImage);
        return this;
    }

    public List<AnswerItem> getItems(){
        AnswerItemList wrapper = params.getParcelable(PARAM_ITEMS);
        if (wrapper != null){
            return wrapper.getItems();
        }
        return null;
    }

    public AnswerItem setItems(List<AnswerItem> items){
        AnswerItemList wrapper = new AnswerItemList(items);
        params.putParcelable(PARAM_ITEMS, wrapper);
        return this;
    }

    public AnswerItem addItem(AnswerItem item){
        List<AnswerItem> items = getItems();
        if (items == null){
            items = new ArrayList<>();
        }
        items.add(item);
        setItems(items);
        return this;
    }

    public List<Hint> getHints(){
        HintList wrapper = params.getParcelable(PARAM_HINTS);
        if (wrapper != null){
            return wrapper.getHints();
        }
        return null;
    }

    public AnswerItem setHints(List<Hint> hints){
        HintList wrapper = new HintList(hints);
        params.putParcelable(PARAM_HINTS, wrapper);
        return this;
    }

    public AnswerItem addHint(Hint hint){
        List<Hint> hints = getHints();
        if (hints == null){
            hints = new ArrayList<>();
        }
        hints.add(hint);
        setHints(hints);
        return this;
    }

    public int getType() {
        return params.getInt(PARAM_TYPE, TYPE_CONVERSATION);
    }

    public AnswerItem setType(int type) {
        params.putInt(PARAM_TYPE, type);
        return this;
    }

    public Command getCommand() {
        return params.getParcelable(PARAM_COMMAND);
    }

    public AnswerItem setCommand(Command command) {
        params.putParcelable(PARAM_COMMAND, command);
        return this;
    }

    public ImageView.ScaleType getImageScaleType() {
        return params.containsKey(PARAM_IMAGE_SCALE_TYPE) ? ImageView.ScaleType.valueOf(params.getString(PARAM_IMAGE_SCALE_TYPE)) : null;
    }

    public AnswerItem setImageScaleType(ImageView.ScaleType imageScaleType) {
        params.putString(PARAM_IMAGE_SCALE_TYPE, imageScaleType.name());
        return this;
    }

    public String getSpeech() {
        return params.getString(PARAM_SPEECH);
    }

    public AnswerItem setSpeech(String text) {
        params.putString(PARAM_SPEECH, text);
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
        this.params = in.readBundle(getClass().getClassLoader());
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
