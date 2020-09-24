package io.antelli.sdk.model

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Handcrafted by Štěpán Šonský on 02.07.2018.
 */
internal class HintList : Parcelable {
    var hints: MutableList<Hint>
        private set

    constructor() {
        hints = ArrayList()
    }

    constructor(hints: MutableList<Hint>) {
        this.hints = hints
    }

    fun add(hint: Hint): HintList {
        hints.add(hint)
        return this
    }

    fun addAll(hint: List<Hint>): HintList {
        hints.addAll(hint)
        return this
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(hints)
    }

    private constructor(parcel: Parcel) {
        hints = parcel.createTypedArrayList(Hint.CREATOR) ?: ArrayList()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HintList> = object : Parcelable.Creator<HintList> {
            override fun createFromParcel(source: Parcel): HintList? {
                return HintList(source)
            }

            override fun newArray(size: Int): Array<HintList?> {
                return arrayOfNulls(size)
            }
        }
    }
}