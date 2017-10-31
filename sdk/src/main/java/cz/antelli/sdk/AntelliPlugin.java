package cz.antelli.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import cz.antelli.sdk.model.Answer;
import cz.antelli.sdk.model.Question;

/**
 * Handcrafted by Štěpán Šonský on 29.08.2017.
 */

public abstract class AntelliPlugin extends Service {

    public static final String ACTION_ANSWER = "cz.antelli.assistant.ANSWER";

    /**
     * Specify the conditions if your service can answer user's Question
     */
    protected abstract boolean canAnswer(Question question) throws RemoteException;

    /**
     * Create Answer for user's Question
     * @param question Input question from the user
     * @return Answer for the user
     * @throws RemoteException
     */
    protected abstract Answer answer(Question question) throws RemoteException;

    /**
     * Create Answer for user's command (AnswerItem onClick or Tip onClick)
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
     * @return Settings Activity Class
     */
    protected abstract Class getSettingsActivity();

    @Override
    public IBinder onBind(Intent intent) {
        return new IAntelliPlugin.Stub() {
            @Override
            public boolean canAnswer(Question question) throws RemoteException {
                return AntelliPlugin.this.canAnswer(question);
            }

            @Override
            public Answer answer(Question question) throws RemoteException {
                return AntelliPlugin.this.answer(question);
            }

            @Override
            public Answer command(String command) throws RemoteException {
                return AntelliPlugin.this.command(command);
            }

            @Override
            public void showSettings() throws RemoteException {
                Class cls = getSettingsActivity();
                if (cls != null) {
                    startActivity(new Intent(AntelliPlugin.this, cls));
                }
            }

            @Override
            public void reset() throws RemoteException{
                AntelliPlugin.this.reset();
            }
        };
    }
}
