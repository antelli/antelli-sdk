package io.antelli.sdk.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */

public class Question implements Parcelable {

    private static final String PARAM_QUERY = "QUERY";
    private static final String PARAM_LANGUAGE = "LANGUAGE";

    private Bundle params = new Bundle();

    public Question(@NonNull String query, @NonNull String language) {
        setQuery(query.toLowerCase());
        setLanguage(language);
    }

    public boolean equals(@NonNull String string) {
        return getQuery().equals(string.toLowerCase());
    }

    public boolean equalsOne(@NonNull String[] strings) {
        for (String string : strings) {
            if (getQuery().equals(string)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(@NonNull String string) {
        return getQuery().contains(string.toLowerCase());
    }

    public boolean containsOne(@NonNull String... strings) {
        for (String string : strings) {
            if (getQuery().contains(string.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(@NonNull String... strings) {
        for (String string : strings) {
            if (!getQuery().contains(string.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean containsWord(@NonNull String word) {
        String input = addSpacePadding(getQuery());
        return input.contains(addSpacePadding(word.toString()));
    }

    public boolean containsOneWord(@NonNull String... words) {
        String input = addSpacePadding(getQuery());
        for (String word : words) {
            if (input.contains(addSpacePadding(word.toLowerCase()))) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAllWords(@NonNull String... words) {
        String input = addSpacePadding(getQuery());
        for (String word : words) {
            if (!input.contains(addSpacePadding(word))) {
                return false;
            }
        }
        return true;
    }

    public boolean startsWith(@NonNull String prefix) {
        return getQuery().startsWith(prefix.toLowerCase());
    }

    public String removeWords(@NonNull String... words) {
        String result = addSpacePadding(getQuery());

        for (String word : words) {
            result = result.replace(addSpacePadding(word), " ");
        }
        return result.trim();

    }

    public String removeWords(@NonNull String[]... words) {
        String result = addSpacePadding(getQuery());

        for (String[] wordArray : words) {
            for (String word : wordArray) {
                result = result.replace(addSpacePadding(word), " ");
            }
        }
        return result.trim();
    }

    public String[] getWords() {
        return getQuery().split(" ");
    }

    public String getWordAt(int position) {
        String[] words = getWords();
        if (position < words.length) {
            return words[position];
        }
        return null;
    }

    public int getWordPosition(String word) {
        String[] words = getWords();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    public int[] getNumbers() {
        List<Integer> list = new ArrayList<>();
        for (String word : getWords()) {
            try {
                int number = Integer.parseInt(word);
                list.add(number);
            } catch (NumberFormatException e) {
                //continue
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void setQuery(String lowerCase) {
        params.putString(PARAM_QUERY, lowerCase);
    }

    public String getQuery() {
        return params.getString(PARAM_QUERY);
    }

    public void setLanguage(String language) {
        params.putString(PARAM_LANGUAGE, language);
    }

    public String getLanguage() {
        return params.getString(PARAM_LANGUAGE);
    }

    public void setParam(String name, String value) {
        params.putString(name, value);
    }

    public String getParam(String name) {
        return params.getString(name);
    }

    private String addSpacePadding(String string) {
        return " " + string + " ";
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
