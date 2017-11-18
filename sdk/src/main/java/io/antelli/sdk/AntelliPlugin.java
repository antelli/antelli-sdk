package io.antelli.sdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Locale;

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
     * @return true if your plugin is able to answer the question
     * @throws RemoteException Implicit AIDL exception
     */
    protected abstract boolean canAnswer(Question question) throws RemoteException;

    /**
     * Create and publish Answer for user's Question using callback.send(Answer)
     *
     * @param question User's question
     * @param callback Use callback.send(Answer) to publish Answer back to Antelli
     * @throws RemoteException Implicit AIDL exception
     */
    protected abstract void answer(Question question, IAnswerCallback callback) throws RemoteException;

    /**
     * Create and publish Answer for user's command using callback.send(Answer)
     *
     * @param command User's command
     * @param callback Use callback.send(Answer) to publish Answer back to Antelli
     * @throws RemoteException Implicit AIDL exception
     */
    protected abstract void command(Command command, IAnswerCallback callback) throws RemoteException;

    /**
     * Reset all variables to default values, if you set some during the conversation
     */
    protected abstract void reset();

    /**
     * Define Settings Activity, if your plugin has enabled settings in Manifest
     *
     * @return Settings Activity Class
     */
    protected abstract Class getSettingsActivity();

    @Override
    public IBinder onBind(Intent intent) {

        return new IAntelliPlugin.Stub() {

            @Override
            public boolean canAnswer(Question question) throws RemoteException {
                if (isAuthorized()) {
                    return AntelliPlugin.this.canAnswer(question);
                }
                return false;
            }

            @Override
            public void answer(Question question, IAnswerCallback callback) throws RemoteException {
                if (isAuthorized()) {
                    AntelliPlugin.this.answer(question, callback);
                }
            }

            @Override
            public void command(Command command, IAnswerCallback callback) throws RemoteException {
                if (isAuthorized()) {
                    AntelliPlugin.this.command(command, callback);
                }
            }

            @Override
            public void showSettings() throws RemoteException {
                if (isAuthorized()) {
                    Class cls = getSettingsActivity();
                    if (cls != null) {
                        startActivity(new Intent(AntelliPlugin.this, cls));
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

    protected String getLocalizedString(int resId, String language) {
        return getLocalizedContext(language).getResources().getString(resId);
    }

    protected String getLocalizedString(int resId, String language, Object... formatArgs) {
        return getLocalizedContext(language).getResources().getString(resId, formatArgs);
    }

    protected String[] getLocalizedStringArray(int resId, String language) {
        return getLocalizedContext(language).getResources().getStringArray(resId);
    }

    private Context getLocalizedContext(String language){
        Configuration conf = getResources().getConfiguration();
        conf = new Configuration(conf);
        conf.setLocale(new Locale(language));
        return createConfigurationContext(conf);
    }
}
