package cz.antelli.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */

public class Question implements Parcelable {

    private String raw;
    private String lowerCase;

    public Question(String raw) {
        this.raw = raw;
        if (raw != null) {
            lowerCase = raw.toLowerCase();
        }
    }

    public String getRaw() {
        return raw;
    }

    public boolean contains(String string) {
        if (lowerCase != null) {
            return getLowerCase().contains(string.toLowerCase());
        }
        return false;
    }

    public boolean containsWord(String word) {
        if (lowerCase != null) {
            String input = addSpacePadding(getLowerCase());
            return input.contains(addSpacePadding(word));
        }
        return false;
    }

    public boolean containsOneOf(String... words) {
        if (lowerCase != null && words != null) {
            String input = addSpacePadding(getLowerCase());
            for (int i = 0; i < words.length; i++) {
                if (input.contains(addSpacePadding(words[i].toLowerCase()))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean containsAllOf(String... words) {
        if (lowerCase != null && words != null) {
            String input = addSpacePadding(getLowerCase());

            for (int i = 0; i < words.length; i++) {
                if (!input.contains(addSpacePadding(words[i]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean startWith(String prefix) {
        if (lowerCase != null) {
            return getLowerCase().startsWith(prefix);
        }
        return false;
    }

    public String removeWords(String... words) {
        if (lowerCase != null) {
            String result = addSpacePadding(getLowerCase());

            if (words !=null) {
                for (int i = 0; i < words.length; i++) {
                    result = result.replace(addSpacePadding(words[i]), " ");
                }
            }
            return result.trim();
        }
        return null;
    }

    public String getLowerCase() {
        return lowerCase;
    }

    private String addSpacePadding(String text) {
        return new StringBuilder().append(" ").append(text).append(" ").toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.raw);
        dest.writeString(this.lowerCase);
    }

    protected Question(Parcel in) {
        this.raw = in.readString();
        this.lowerCase = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
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
