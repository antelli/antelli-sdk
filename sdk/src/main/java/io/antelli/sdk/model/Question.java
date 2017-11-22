package io.antelli.sdk.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */

public class Question implements Parcelable {

    private static final String PARAM_STRING = "STRING";
    private static final String PARAM_LANGUAGE = "LANGUAGE";

    private Bundle params = new Bundle();

    public Question(String string, String language) {
        if (string != null) {
            setString(string.toLowerCase());
        }
        setLanguage(language);
    }

    public boolean equals(String string) {
        if (notNull()) {
            if (string != null) {
                return getString().equals(string.toLowerCase());
            }
        } else if (string == null) {
            return true;
        }
        return false;
    }

    public boolean contains(String string) {
        if (notNull()) {
            return getString().contains(string.toLowerCase());
        }
        return false;
    }

    public boolean containsOne(String... strings) {
        if (notNull() && strings != null) {
            for (int i = 0; i < strings.length; i++) {
                if (getString().contains(strings[i].toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean containsAll(String... strings) {
        if (notNull() && strings != null) {
            for (int i = 0; i < strings.length; i++) {
                if (!getString().contains(strings[i].toLowerCase())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean containsWord(String word) {
        if (notNull()) {
            String input = addSpacePadding(getString());
            return input.contains(addSpacePadding(word));
        }
        return false;
    }

    public boolean containsOneWord(String... words) {
        if (notNull() && words != null) {
            String input = addSpacePadding(getString());
            for (int i = 0; i < words.length; i++) {
                if (input.contains(addSpacePadding(words[i].toLowerCase()))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean containsAllWords(String... words) {
        if (notNull() && words != null) {
            String input = addSpacePadding(getString());

            for (int i = 0; i < words.length; i++) {
                if (!input.contains(addSpacePadding(words[i]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        if (notNull()) {
            return getString().startsWith(prefix);
        }
        return false;
    }

    public String removeWords(String... words) {
        if (notNull()) {
            String result = addSpacePadding(getString());

            if (words != null) {
                for (int i = 0; i < words.length; i++) {
                    result = result.replace(addSpacePadding(words[i]), " ");
                }
            }
            return result.trim();
        }
        return null;
    }

    private void setString(String lowerCase) {
        params.putString(PARAM_STRING, lowerCase);
    }

    public String getString() {
        return params.getString(PARAM_STRING);
    }

    private void setLanguage(String language) {
        params.putString(PARAM_LANGUAGE, language);
    }

    public String getLanguage() {
        return params.getString(PARAM_LANGUAGE);
    }

    private boolean notNull() {
        return params.containsKey(PARAM_STRING);
    }

    private String addSpacePadding(String string) {
        return new StringBuilder().append(" ").append(string).append(" ").toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(this.params);
    }

    protected Question(Parcel in) {
        this.params = in.readBundle(getClass().getClassLoader());
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
