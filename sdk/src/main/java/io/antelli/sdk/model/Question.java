package io.antelli.sdk.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */

public class Question implements Parcelable {

    private static final String PARAM_QUERY = "QUERY";
    private static final String PARAM_LANGUAGE = "LANGUAGE";
    private static final String PARAM_ACTION = "ACTION";

    private Bundle params = new Bundle();

    public Question(String query, String language) {
        if (query != null) {
            setQuery(query.toLowerCase());
        }
        setLanguage(language);
    }

    public boolean equals(String string) {
        if (notNull() && string != null) {
            return getQuery().equals(string.toLowerCase());
        }
        return false;
    }

    public boolean contains(String string) {
        if (notNull() && string != null) {
            return getQuery().contains(string.toLowerCase());
        }
        return false;
    }

    public boolean containsOne(String... strings) {
        if (notNull() && strings != null) {
            for (int i = 0; i < strings.length; i++) {
                if (getQuery().contains(strings[i].toLowerCase())) {
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
                if (!getQuery().contains(strings[i].toLowerCase())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean containsWord(String word) {
        if (notNull() && word != null) {
            String input = addSpacePadding(getQuery());
            return input.contains(addSpacePadding(word.toString()));
        }
        return false;
    }

    public boolean containsOneWord(String... words) {
        if (notNull() && words != null) {
            String input = addSpacePadding(getQuery());
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
            String input = addSpacePadding(getQuery());

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
        if (notNull() && prefix != null) {
            return getQuery().startsWith(prefix.toLowerCase());
        }
        return false;
    }

    public String removeWords(String... words) {
        if (notNull()) {
            String result = addSpacePadding(getQuery());

            if (words != null) {
                for (int i = 0; i < words.length; i++) {
                    result = result.replace(addSpacePadding(words[i]), " ");
                }
            }
            return result.trim();
        }
        return null;
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

    public void setAction(String action) {
        params.putString(PARAM_ACTION, action);
    }

    public String getAction() {
        return params.getString(PARAM_ACTION);
    }

    public boolean hasAction(String action) {
        String questionAction = getAction();
        if (questionAction != null) {
            return questionAction.equals(action);
        }
        return false;
    }

    public boolean hasAction(String... actions) {
        String questionAction = getAction();
        if (questionAction != null && actions != null) {
            for (int i = 0; i < actions.length; i++) {
                if (questionAction.equals(actions[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean notNull() {
        return params.containsKey(PARAM_QUERY);
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
