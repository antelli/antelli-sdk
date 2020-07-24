package io.antelli.sdk.model

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */
class Question : Parcelable {
    private var params = Bundle()

    constructor(query: String, language: String) {
        this.query = query.toLowerCase()
        this.language = language
    }

    fun equals(string: String): Boolean {
        return query == string.toLowerCase()
    }

    fun equalsOne(strings: Array<String>): Boolean {
        for (string in strings) {
            if (query == string) {
                return true
            }
        }
        return false
    }

    operator fun contains(string: String): Boolean {
        return query.contains(string.toLowerCase())
    }

    fun containsOne(vararg strings: String): Boolean {
        for (string in strings) {
            if (string != null) {
                if (query.contains(string.toLowerCase())) {
                    return true
                }
            }
        }
        return false
    }

    fun containsAll(vararg strings: String): Boolean {
        for (string in strings) {
            if (string != null && !query.contains(string.toLowerCase())) {
                return false
            }
        }
        return true
    }

    fun containsWord(word: String): Boolean {
        val input = addSpacePadding(query)
        return input.contains(addSpacePadding(word))
    }

    fun containsOneWord(vararg words: String): Boolean {
        val input = addSpacePadding(query)
        for (word in words) {
            if (word != null && input.contains(addSpacePadding(word.toLowerCase()))) {
                return true
            }
        }
        return false
    }

    fun containsAllWords(vararg words: String): Boolean {
        val input = addSpacePadding(query)
        for (word in words) {
            if (word != null && !input.contains(addSpacePadding(word))) {
                return false
            }
        }
        return true
    }

    fun startsWith(prefix: String): Boolean {
        return query.startsWith(prefix.toLowerCase())
    }

    fun removeWords(vararg words: String): String {
        var result = addSpacePadding(query)
        for (word in words) {
            if (word != null) {
                result = result.replace(addSpacePadding(word), " ")
            }
        }
        return result.trim { it <= ' ' }
    }

    fun removeWords(vararg words: Array<String>): String {
        var result = addSpacePadding(query)
        for (wordArray in words) {
            if (wordArray != null) {
                for (word in wordArray) {
                    if (word != null) {
                        result = result.replace(addSpacePadding(word), " ")
                    }
                }
            }
        }
        return result.trim { it <= ' ' }
    }

    val words: Array<String>
        get() = query.split(" ".toRegex()).toTypedArray()

    fun getWordAt(position: Int): String? {
        val words = words
        return if (position < words.size) {
            words[position]
        } else null
    }

    fun getWordPosition(word: String): Int {
        val words = words
        for (i in words.indices) {
            if (word == words[i]) {
                return i
            }
        }
        return -1
    }

    //continue
    val numbers: IntArray
        get() {
            val list: MutableList<Int> = ArrayList()
            for (word in words) {
                try {
                    val number = word.toInt()
                    list.add(number)
                } catch (e: NumberFormatException) {
                    //continue
                }
            }
            val result = IntArray(list.size)
            for (i in list.indices) {
                result[i] = list[i]
            }
            return result
        }

    var query: String
        get() = params.getString(PARAM_QUERY)
        private set(lowerCase) {
            params.putString(PARAM_QUERY, lowerCase)
        }

    var language: String
        get() = params.getString(PARAM_LANGUAGE)
        set(language) {
            params.putString(PARAM_LANGUAGE, language)
        }

    fun setParam(name: String, value: String) {
        params.putString(name, value)
    }

    fun getParam(name: String?): String {
        return params.getString(name)
    }

    private fun addSpacePadding(string: String): String {
        return " $string "
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeBundle(params)
    }

    protected constructor(`in`: Parcel) {
        params = `in`.readBundle(javaClass.classLoader)
    }

    companion object {
        private const val PARAM_QUERY = "QUERY"
        private const val PARAM_LANGUAGE = "LANGUAGE"
        @JvmField
        val CREATOR: Parcelable.Creator<Question> = object : Parcelable.Creator<Question> {
            override fun createFromParcel(source: Parcel): Question? {
                return Question(source)
            }

            override fun newArray(size: Int): Array<Question?> {
                return arrayOfNulls(size)
            }
        }
    }
}