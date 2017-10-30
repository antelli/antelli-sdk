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

    protected abstract boolean canAnswer(Question question) throws RemoteException;
    protected abstract Answer answer(Question question) throws RemoteException;
    protected abstract void reset();

    protected Answer command(String command) throws RemoteException{
        return null;
    }

    protected Class getSettingsActivity(){
        return null;
    }

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
