package io.antelli.sdk.model

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

/**
 * Handcrafted by Štěpán Šonský on 27.08.2017.
 */
open class Answer : Parcelable {
    var items: MutableList<AnswerItem> = ArrayList()
    private set
    private var params = Bundle()

    constructor() {}
    constructor(text: String?) {
        addItem(AnswerItem().setText(text).setSpeech(text))
    }

    constructor(items: List<AnswerItem>) {
        this.items.addAll(items)
    }

    fun addItem(item: AnswerItem): Answer {
        items.add(item)
        return this
    }

    fun addItems(item: List<AnswerItem>): Answer {
        items.addAll(item)
        return this
    }

    val isAutoListen: Boolean
        get() = params.getBoolean(PARAM_AUTO_LISTEN, false)

    fun setAutoListen(autoListen: Boolean): Answer {
        params.putBoolean(PARAM_AUTO_LISTEN, autoListen)
        return this
    }

    fun setAutoRun(intent: Intent?): Answer {
        params.putParcelable(PARAM_AUTO_RUN, intent)
        return this
    }

    val autoRun: Intent
        get() = params.getParcelable(PARAM_AUTO_RUN)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(items)
        dest.writeBundle(params)
    }

    protected constructor(`in`: Parcel) {
        items = `in`.createTypedArrayList(AnswerItem.CREATOR) ?: ArrayList()
        params = `in`.readBundle(javaClass.classLoader) ?: Bundle()
    }

    companion object {
        private const val PARAM_AUTO_LISTEN = "AUTO_LISTEN"
        private const val PARAM_AUTO_RUN = "AUTO_RUN"
        @JvmField
        val CREATOR: Parcelable.Creator<Answer> = object : Parcelable.Creator<Answer> {
            override fun createFromParcel(source: Parcel): Answer? {
                return Answer(source)
            }

            override fun newArray(size: Int): Array<Answer?> {
                return arrayOfNulls(size)
            }
        }
    }
}