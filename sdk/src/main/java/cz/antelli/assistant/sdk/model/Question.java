package cz.antelli.assistant.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by stepan on 28.08.2017.
 */

public class Question implements Parcelable {

    private String raw;

    public Question(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }

    public boolean contains(String string){
        return getLowerCase().contains(string.toLowerCase());
    }

    public boolean containsOneOf(String... words){
        String lowerCase = " " + getLowerCase() + " ";
        for (int i = 0; i < words.length; i++) {
            if (lowerCase.contains(" " + words[i].toLowerCase() + " ")) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAllOf(String... words){
        String lowerCase = " " + getLowerCase() + " ";
        for (int i = 0; i < words.length; i++) {
            if (!lowerCase.contains(" " + words[i].toLowerCase() + " ")) {
                return false;
            }
        }
        return true;
    }

    public boolean startWith(String prefix){
        return getLowerCase().startsWith(prefix);
    }

    public String removeWords(String... words){
        String result = " " + getLowerCase() + " ";
        for (int i = 0; i < words.length; i++) {
            result = result.replace((" " + words[i] + " "), " ");
        }
        return result.trim();
    }

    public String getLowerCase() {
        return raw.toLowerCase();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.raw);
    }

    protected Question(Parcel in) {
        this.raw = in.readString();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
