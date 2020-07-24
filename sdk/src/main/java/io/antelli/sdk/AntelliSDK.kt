package io.antelli.sdk

/**
 * Handcrafted by Štěpán Šonský on 29.08.2017.
 */
object AntelliSDK {
    @JvmStatic
    val apiVersion: IntArray get() = intArrayOf(BuildConfig.VERSION_MAJOR, BuildConfig.VERSION_MINOR, BuildConfig.VERSION_PATCH)
}