package io.antelli.sdk

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.RemoteException
import androidx.annotation.RequiresApi
import io.antelli.sdk.AntelliPlugin
import io.antelli.sdk.callback.IAnswerCallback
import io.antelli.sdk.callback.ICanAnswerCallback
import io.antelli.sdk.model.Command
import io.antelli.sdk.model.Question
import java.util.*
import kotlin.reflect.KClass

/**
 * Handcrafted by Štěpán Šonský on 29.08.2017.
 */
abstract class AntelliPlugin : Service() {

    companion object {
        private const val ANTELLI_PACKAGE_NAME = "io.antelli.assistant"
        const val ACTION_ANSWER = "io.antelli.assistant.ANSWER"
    }
    /**
     * Specify the conditions if your service can answer user's Question
     *
     * @param question User's question
     * @param callback Use callback.canAnswer(Boolean) to tell Antelli, wheter your plugin can answer the question or not
     * @throws RemoteException Implicit AIDL exception
     */
    @Throws(RemoteException::class)
    protected abstract fun canAnswer(question: Question, callback: ICanAnswerCallback)

    /**
     * Create and publish Answer for user's Question using callback.send(Answer)
     *
     * @param question User's question
     * @param callback Use callback.answer(Answer) to publish Answer back to Antelli
     * @throws RemoteException Implicit AIDL exception
     */
    @Throws(RemoteException::class)
    protected abstract fun answer(question: Question, callback: IAnswerCallback)

    /**
     * Create and publish Answer for user's command using callback.send(Answer)
     *
     * @param command User's command
     * @param callback Use callback.answer(Answer) to publish Answer back to Antelli
     * @throws RemoteException Implicit AIDL exception
     */
    @Throws(RemoteException::class)
    protected abstract fun command(command: Command, callback: IAnswerCallback)

    /**
     * Reset all variables to default values, if you set some during the conversation
     */
    protected abstract fun reset()

    /**
     * Define Settings Activity, if your plugin has enabled settings in Manifest
     *
     * @return Settings Activity Class
     */
    protected open val settingsActivity: KClass<out Activity>?
        protected get() = null

    override fun onBind(intent: Intent): IBinder {
        return object : IAntelliPlugin.Stub() {
            @Throws(RemoteException::class)
            override fun canAnswer(question: Question, callback: ICanAnswerCallback) {
                if (isAuthorized) {
                    this@AntelliPlugin.canAnswer(question, callback)
                }
            }

            @Throws(RemoteException::class)
            override fun answer(question: Question, callback: IAnswerCallback) {
                if (isAuthorized) {
                    this@AntelliPlugin.answer(question, callback)
                }
            }

            @Throws(RemoteException::class)
            override fun command(command: Command, callback: IAnswerCallback) {
                if (isAuthorized) {
                    this@AntelliPlugin.command(command, callback)
                }
            }

            override fun showSettings() {
                if (isAuthorized) {
                    val cls = settingsActivity
                    if (cls != null) {
                        val settingsiIntent = Intent(this@AntelliPlugin, cls.java)
                        settingsiIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(settingsiIntent)
                    }
                }
            }

            override fun reset() {
                if (isAuthorized) {
                    this@AntelliPlugin.reset()
                }
            }
        }
    }

    private val isAuthorized: Boolean
        private get() {
            val pm = packageManager
            val packageName = pm.getNameForUid(Binder.getCallingUid())
            return packageName != null && packageName == ANTELLI_PACKAGE_NAME
        }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected fun getContext(language: String): Context {
        var conf = resources.configuration
        conf = Configuration(conf)
        conf.setLocale(Locale(language.replace("-", "_")))
        return createConfigurationContext(conf)
    }
}