package io.antelli.sdk.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */

public class Question implements Parcelable {

    private static final String PARAM_QUERY = "QUERY";
    private static final String PARAM_LANGUAGE = "LANGUAGE";

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

    public boolean equalsOne(String[] strings) {
        if (notNull() && strings != null) {
            for (String string : strings) {
                if (getQuery().equals(string)) {
                    return true;
                }
            }
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
            for (String string : strings) {
                if (string != null) {
                    if (getQuery().contains(string.toLowerCase())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public boolean containsAll(String... strings) {
        if (notNull() && strings != null) {
            for (String string : strings) {
                if (string != null && !getQuery().contains(string.toLowerCase())) {
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
            for (String word : words) {
                if (word != null && input.contains(addSpacePadding(word.toLowerCase()))) {
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

            for (String word : words) {
                if (word != null && !input.contains(addSpacePadding(word))) {
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
            if (words != null) {
                String result = addSpacePadding(getQuery());
                for (String word : words) {
                    if (word != null) {
                        result = result.replace(addSpacePadding(word), " ");
                    }
                }
                return result.trim();
            } else {
                return getQuery();
            }
        }
        return null;
    }

    public String removeWords(String[]... words) {
        if (notNull()) {
            if (words != null) {
                String result = addSpacePadding(getQuery());
                for (String[] wordArray : words) {
                    if (wordArray != null) {
                        for (String word : wordArray) {
                            if (word != null) {
                                result = result.replace(addSpacePadding(word), " ");
                            }
                        }
                    }
                }
                return result.trim();
            } else {
                return getQuery();
            }
        }
        return null;
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
        if (word != null) {
            String[] words = getWords();
            for (int i = 0; i < words.length; i++) {
                if (word.equals(words[i])) {
                    return i;
                }
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
