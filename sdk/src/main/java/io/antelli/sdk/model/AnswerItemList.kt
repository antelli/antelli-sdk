package io.antelli.sdk.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Handcrafted by Štěpán Šonský on 12.11.2017.
 */
internal class AnswerItemList : Parcelable {
    var items: MutableList<AnswerItem>
    private set

    constructor(items: MutableList<AnswerItem>) {
        this.items = items
    }

    fun add(item: AnswerItem): AnswerItemList {
        items.add(item)
        return this
    }

    fun addAll(item: List<AnswerItem>): AnswerItemList {
        items.addAll(item)
        return this
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(items)
    }

    private constructor(parcel: Parcel) {
        items = parcel.createTypedArrayList(AnswerItem.CREATOR) ?: ArrayList()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AnswerItemList> = object : Parcelable.Creator<AnswerItemList> {
            override fun createFromParcel(source: Parcel): AnswerItemList? {
                return AnswerItemList(source)
            }

            override fun newArray(size: Int): Array<AnswerItemList?> {
                return arrayOfNulls(size)
            }
        }
    }
}