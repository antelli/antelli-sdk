package io.antelli.sdk.model

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

/**
 * Handcrafted by Štěpán Šonský on 11.11.2017.
 */
class Command : Parcelable {
    private var params = Bundle()

    constructor(action: String?) {
        params.putString(PARAM_ACTION, action)
    }

    constructor(intent: Intent?) {
        params.putString(PARAM_ACTION, ACTION_INTENT)
        params.putParcelable(PARAM_INTENT, intent)
    }

    val action: String?
        get() = params.getString(PARAM_ACTION)

    fun putBoolean(name: String, value: Boolean) {
        params.putBoolean(name, value)
    }

    fun putInt(name: String, value: Int) {
        params.putInt(name, value)
    }

    fun putDouble(name: String, value: Double) {
        params.putDouble(name, value)
    }

    fun putLong(name: String, value: Long) {
        params.putLong(name, value)
    }

    fun putString(name: String, value: String?) {
        params.putString(name, value)
    }

    fun putParcelable(name: String, value: Parcelable?) {
        params.putParcelable(name, value)
    }

    fun getBoolean(name: String): Boolean? {
        return params.getBoolean(name)
    }

    fun getInt(name: String): Int? {
        return params.getInt(name)
    }

    fun getLong(name: String): Long? {
        return params.getLong(name)
    }

    fun getDouble(name: String): Double? {
        return params.getDouble(name)
    }

    fun getString(name: String): String? {
        return params.getString(name)
    }

    fun getParcelable(name: String): Parcelable? {
        return params.getParcelable(name)
    }

    val intent: Intent?
        get() = params.getParcelable(PARAM_INTENT)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeBundle(params)
    }

    protected constructor(`in`: Parcel) {
        params = `in`.readBundle(javaClass.classLoader) ?: Bundle()
    }

    companion object {
        private const val PARAM_ACTION = "ACTION"
        private const val PARAM_INTENT = "INTENT"
        private const val ACTION_INTENT = "intent"
        @JvmField
        val CREATOR: Parcelable.Creator<Command> = object : Parcelable.Creator<Command> {
            override fun createFromParcel(source: Parcel): Command? {
                return Command(source)
            }

            override fun newArray(size: Int): Array<Command?> {
                return arrayOfNulls(size)
            }
        }
    }
}