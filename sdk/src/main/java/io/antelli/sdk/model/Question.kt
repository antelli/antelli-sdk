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

    var query: String
        get() = params.getString(PARAM_QUERY)!!
        private set(query) {
            params.putString(PARAM_QUERY, query)
        }

    var language: String
        get() = params.getString(PARAM_LANGUAGE)!!
        set(language) {
            params.putString(PARAM_LANGUAGE, language)
        }

    val words: List<String>
        get() {
            return query.split(" ")
        }

    val numbers : List<Int>
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
            return list
        }

    constructor(query: String, language: String) {
        this.query = query
        this.language = language
    }

    fun equals(string: String): Boolean {
        return query.equals(string, true)
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
        return query.contains(string, true)
    }

    fun containsOne(vararg strings: String): Boolean {
        for (string in strings) {
            if (query.contains(string, true)) {
                return true
            }
        }
        return false
    }

    fun containsAll(vararg strings: String): Boolean {
        for (string in strings) {
            if (!query.contains(string, true)) {
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
            if (input.contains(addSpacePadding(word), true)) {
                return true
            }
        }
        return false
    }

    fun containsAllWords(vararg words: String): Boolean {
        val input = addSpacePadding(query)
        for (word in words) {
            if (!input.contains(addSpacePadding(word), true)) {
                return false
            }
        }
        return true
    }

    fun startsWith(prefix: String): Boolean {
        return query.startsWith(prefix, true)
    }

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

    fun removeWords(vararg words: String): String {
        var result = addSpacePadding(query)
        for (word in words) {
            result = result.replace(addSpacePadding(word), " ")
        }
        return result.trim { it <= ' ' }
    }

    fun removeWords(vararg words: Array<String>): String {
        var result = addSpacePadding(query)
        for (wordArray in words) {
            for (word in wordArray) {
                result = result.replace(addSpacePadding(word), " ")
            }
        }
        return result.trim(' ')
    }

    fun setParam(name: String, value: String) {
        params.putString(name, value)
    }

    fun getParam(name: String?): String? {
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

    private constructor(parcel: Parcel) {
        params = parcel.readBundle(javaClass.classLoader) ?: Bundle()
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