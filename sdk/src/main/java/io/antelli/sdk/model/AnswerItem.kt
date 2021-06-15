package io.antelli.sdk.model

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import java.util.*
import kotlin.collections.ArrayList

/**
 * Handcrafted by Štěpán Šonský on 27.08.2017.
 */
class AnswerItem : Parcelable {
    private var params = Bundle()

    constructor()

    constructor(textAndSpeech: String?) {
        this.textAndSpeech = textAndSpeech
    }

    var text: String?
        get() = params.getString(PARAM_TEXT)
        set(value) = params.putString(PARAM_TEXT, value)

    private var textAndSpeech: String?
        get() = text
        set(value) {
            params.putString(PARAM_TEXT, value)
            params.putString(PARAM_SPEECH, value)
        }

    var largeText: String?
        get() = params.getString(PARAM_LARGE_TEXT)
        set(value) = params.putString(PARAM_LARGE_TEXT, value)

    var title: String?
        get() = params.getString(PARAM_TITLE)
        set(value) = params.putString(PARAM_TITLE, value)

    var subtitle: String?
        get() = params.getString(PARAM_SUBTITLE)
        set(value) = params.putString(PARAM_SUBTITLE, value)

    var image: String?
        get() = params.getString(PARAM_IMAGE)
        set(value) = params.putString(PARAM_IMAGE, value)

    var secondaryImage: String?
        get() = params.getString(PARAM_SECONDARY_IMAGE)
        set(value) = params.putString(PARAM_SECONDARY_IMAGE, value)

    var items: MutableList<AnswerItem>?
        get() {
            val wrapper: AnswerItemList? = params.getParcelable(PARAM_ITEMS)
            return wrapper?.items
        }
        set(value) {
            params.putParcelable(PARAM_ITEMS, if (value != null) {
                AnswerItemList(value)
            } else null)
        }

    fun addItem(item: AnswerItem) {
        if (items == null) {
            items = ArrayList()
        }
        items?.add(item)
    }

    var hints: MutableList<Hint>?
        get() {
            val wrapper: HintList? = params.getParcelable(PARAM_HINTS)
            return wrapper?.hints
        }
        set(value) {
            params.putParcelable(PARAM_HINTS, if (value != null) {
                HintList(value)
            } else {
                null
            })
        }


    var type: Int
        get() = params.getInt(PARAM_TYPE, TYPE_CONVERSATION)
        set(value) = params.putInt(PARAM_TYPE, value)

    var command: Command?
        get() = params.getParcelable(PARAM_COMMAND)
        set(value) = params.putParcelable(PARAM_COMMAND, value)

    var imageScaleType: ImageView.ScaleType?
        get() = if (params.containsKey(PARAM_IMAGE_SCALE_TYPE)) ImageView.ScaleType.valueOf(params.getString(PARAM_IMAGE_SCALE_TYPE)!!) else null
        set(value) = params.putString(PARAM_IMAGE_SCALE_TYPE, value?.name)

    var stream: String?
        get() = params.getString(PARAM_STREAM)
        set(value) {
            params.putString(PARAM_STREAM, value)
        }

    var speech: String?
        get() = params.getString(PARAM_SPEECH)
        set(value) = params.putString(PARAM_SPEECH, value)

    fun addHint(hint: Hint) {
        var hints = this.hints
        if (hints == null) {
            hints = ArrayList()
        }
        hints.add(hint)
        this.hints = hints
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
        private const val PARAM_TITLE = "TITLE"
        private const val PARAM_SUBTITLE = "SUBTITLE"
        private const val PARAM_TEXT = "TEXT"
        private const val PARAM_LARGE_TEXT = "LARGE_TEXT"
        private const val PARAM_IMAGE = "IMAGE"
        private const val PARAM_SECONDARY_IMAGE = "SECONDARY_IMAGE"
        private const val PARAM_IMAGE_SCALE_TYPE = "IMAGE_SCALE_TYPE"
        private const val PARAM_COMMAND = "COMMAND"
        private const val PARAM_SPEECH = "SPEECH"
        private const val PARAM_ITEMS = "ITEMS"
        private const val PARAM_HINTS = "HINTS"
        private const val PARAM_TYPE = "TYPE"
        private const val PARAM_STREAM = "STREAM"
        const val TYPE_CONVERSATION = 0
        const val TYPE_CARD = 1
        const val TYPE_IMAGE = 2
        const val TYPE_CAROUSEL_SMALL = 3
        const val TYPE_CAROUSEL_MEDIUM = 4
        const val TYPE_CAROUSEL_LARGE = 5
        const val TYPE_AUDIO = 6
        const val TYPE_VIDEO = 7

        @JvmField
        val CREATOR: Parcelable.Creator<AnswerItem> = object : Parcelable.Creator<AnswerItem> {
            override fun createFromParcel(source: Parcel): AnswerItem? {
                return AnswerItem(source)
            }

            override fun newArray(size: Int): Array<AnswerItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}