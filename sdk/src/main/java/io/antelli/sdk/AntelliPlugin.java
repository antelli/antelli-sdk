package io.antelli.sdk;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.antelli.sdk.callback.ICanAnswerCallback;
import io.antelli.sdk.callback.IAnswerCallback;
import io.antelli.sdk.model.Command;
import io.antelli.sdk.model.Question;

import static android.os.Binder.getCallingUid;

/**
 * Handcrafted by Štěpán Šonský on 29.08.2017.
 */

public abstract class AntelliPlugin extends Service {

    private static final String ANTELLI_PACKAGE_NAME = "io.antelli.assistant";
    public static final String ACTION_ANSWER = "io.antelli.assistant.ANSWER";

    /**
     * Specify the conditions if your service can answer user's Question
     * @param question User's question
     * @param callback Use callback.canAnswer(Boolean) to tell Antelli, wheter your plugin can answer the question or not
     * @throws RemoteException Implicit AIDL exception
     */
    protected abstract void canAnswer(@NonNull Question question, @NonNull ICanAnswerCallback callback) throws RemoteException;

    /**
     * Create and publish Answer for user's Question using callback.send(Answer)
     *
     * @param question User's question
     * @param callback Use callback.answer(Answer) to publish Answer back to Antelli
     * @throws RemoteException Implicit AIDL exception
     */
    protected abstract void answer(@NonNull Question question, @NonNull IAnswerCallback callback) throws RemoteException;

    /**
     * Create and publish Answer for user's command using callback.send(Answer)
     *
     * @param command User's command
     * @param callback Use callback.answer(Answer) to publish Answer back to Antelli
     * @throws RemoteException Implicit AIDL exception
     */
    protected abstract void command(@NonNull Command command, @NonNull IAnswerCallback callback) throws RemoteException;

    /**
     * Reset all variables to default values, if you set some during the conversation
     */
    protected abstract void reset() throws RemoteException;

    /**
     * Define Settings Activity, if your plugin has enabled settings in Manifest
     *
     * @return Settings Activity Class
     */
    protected @Nullable Class<? extends Activity> getSettingsActivity(){
        return null;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new IAntelliPlugin.Stub() {

            @Override
            public void canAnswer(@NonNull Question question, @NonNull ICanAnswerCallback callback) throws RemoteException {
                if (isAuthorized()) {
                    AntelliPlugin.this.canAnswer(question, callback);
                }
            }

            @Override
            public void answer(@NonNull Question question, @NonNull IAnswerCallback callback) throws RemoteException {
                if (isAuthorized()) {
                    AntelliPlugin.this.answer(question, callback);
                }
            }

            @Override
            public void command(@NonNull Command command, @NonNull IAnswerCallback callback) throws RemoteException {
                if (isAuthorized()) {
                    AntelliPlugin.this.command(command, callback);
                }
            }

            @Override
            public void showSettings() throws RemoteException {
                if (isAuthorized()) {
                    Class cls = getSettingsActivity();
                    if (cls != null) {
                        Intent intent = new Intent(AntelliPlugin.this, cls);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void reset() throws RemoteException {
                if (isAuthorized()) {
                    AntelliPlugin.this.reset();
                }
            }
        };
    }

    private boolean isAuthorized() {
        PackageManager pm = getPackageManager();
        String packageName = pm.getNameForUid(getCallingUid());
        return (packageName != null && packageName.equals(ANTELLI_PACKAGE_NAME));
    }

    protected Context getContext(String language){
        Configuration conf = getResources().getConfiguration();
        conf = new Configuration(conf);
        conf.setLocale(new Locale(language.replace("-", "_")));
        return createConfigurationContext(conf);
    }
}
