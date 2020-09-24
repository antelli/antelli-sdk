package io.antelli.sdk.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Handcrafted by Štěpán Šonský on 28.08.2017.
 */
class Hint() : Parcelable {
    var title: String? = null
    var command: Command? = null

    constructor(title: String?) : this() {
        this.title = title
    }

    constructor(title: String?, command: Command?) : this() {
        this.title = title
        this.command = command
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeParcelable(command, flags)
    }

    private constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        command = parcel.readParcelable(Command::class.java.classLoader)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Hint> = object : Parcelable.Creator<Hint> {
            override fun createFromParcel(source: Parcel): Hint? {
                return Hint(source)
            }

            override fun newArray(size: Int): Array<Hint?> {
                return arrayOfNulls(size)
            }
        }
    }
}