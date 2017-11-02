package io.antelli.sdk;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;

import io.antelli.sdk.IAntelliPlugin;
import io.antelli.sdk.model.Answer;
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
     */
    protected abstract boolean canAnswer(Question question) throws RemoteException;

    /**
     * Create Answer for user's Question
     *
     * @param question Input question from the user
     * @return Answer for the user
     * @throws RemoteException
     */
    protected abstract Answer answer(Question question) throws RemoteException;

    /**
     * Create Answer for user's command (AnswerItem onClick or Tip onClick)
     *
     * @param command Command from AnswerItem or Tip
     * @return Answer for the user
     * @throws RemoteException
     */
    protected abstract Answer command(String command) throws RemoteException;

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
            public Answer answer(Question question) throws RemoteException {
                if (isAuthorized()) {
                    return AntelliPlugin.this.answer(question);
                }
                return null;
            }

            @Override
            public Answer command(String command) throws RemoteException {
                if (isAuthorized()) {
                    return AntelliPlugin.this.command(command);
                }
                return null;
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
}
