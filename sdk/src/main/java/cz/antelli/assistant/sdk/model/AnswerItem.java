package cz.antelli.assistant.sdk.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepan on 27.08.2017.
 */

public class AnswerItem implements Parcelable {


    public static final int TYPE_CONVERSATION = 1;
    public static final int TYPE_CARD = 2;

    public static final int IMAGE_CROP = 1;
    public static final int IMAGE_FIT = 2;

    public static final int TEXT_SMALL = 2;

    private String title;
    private String subtitle;
    private String text;
    private String image;
    private int imageScaleType = IMAGE_CROP;
    private Uri command;
    private List<String> speech;
    private int type = TYPE_CONVERSATION;

    public List<String> getSpeech() {
        return speech;
    }

    public AnswerItem speak(String text){
        if (speech == null){
            speech = new ArrayList<>();
        }
        speech.add(text);
        return this;
    }

    public AnswerItem() {
    }

    public String getText() {
        return text;
    }

    public AnswerItem setText(String text) {
        this.text = text;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AnswerItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public AnswerItem setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getImage() {
        return image;
    }

    public AnswerItem setImage(String image) {
        this.image = image;
        return this;
    }

    public int getType() {
        return type;
    }

    public AnswerItem setType(int type) {
        this.type = type;
        return this;
    }

    public Uri getCommand() {
        return command;
    }

    public AnswerItem setCommand(Uri command) {
        this.command = command;
        return this;
    }

    public int getImageScaleType() {
        return imageScaleType;
    }

    public AnswerItem setImageScaleType(int imageScaleType) {
        this.imageScaleType = imageScaleType;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.text);
        dest.writeString(this.image);
        dest.writeInt(this.imageScaleType);
        dest.writeParcelable(this.command, flags);
        dest.writeStringList(this.speech);
        dest.writeInt(this.type);
    }

    protected AnswerItem(Parcel in) {
        this.title = in.readString();
        this.subtitle = in.readString();
        this.text = in.readString();
        this.image = in.readString();
        this.imageScaleType = in.readInt();
        this.command = in.readParcelable(Uri.class.getClassLoader());
        this.speech = in.createStringArrayList();
        this.type = in.readInt();
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
