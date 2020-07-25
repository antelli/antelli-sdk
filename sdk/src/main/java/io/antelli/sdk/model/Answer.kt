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

    constructor()
    constructor(text: String?) {
        addItem(AnswerItem().apply {
            this.text = text
            this.speech = text
        })
    }

    constructor(items: List<AnswerItem>) {
        this.items.addAll(items)
    }

    fun addItem(item: AnswerItem) {
        items.add(item)
    }

    fun addItems(item: List<AnswerItem>) {
        items.addAll(item)
    }

    var isAutoListen: Boolean
        get() = params.getBoolean(PARAM_AUTO_LISTEN, false)
        set(value) = params.putBoolean(PARAM_AUTO_LISTEN, value)

    var autoRun: Intent?
        get() = params.getParcelable(PARAM_AUTO_RUN)
        set(value) = params.putParcelable(PARAM_AUTO_RUN, value)

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