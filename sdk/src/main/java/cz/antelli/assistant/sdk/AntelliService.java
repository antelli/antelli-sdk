package cz.antelli.assistant.sdk;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

import cz.antelli.assistant.sdk.model.Answer;
import cz.antelli.assistant.sdk.model.Question;

/**
 * Created by stepan on 29.08.2017.
 */

public abstract class AntelliService extends Service {

    public static final String ACTION_ANSWER = "cz.antelli.assistant.ANSWER";

    public abstract boolean canAnswer(Question question) throws RemoteException;
    public abstract Answer answer(Question question) throws RemoteException;
    protected void reset(){}

    public boolean canAnswerCommand(Uri command) throws RemoteException{
        return false;
    }
    public Answer answerCommand(Uri command) throws RemoteException{
        return null;
    }

    public void showSettings(){}

    @Override
    public IBinder onBind(Intent intent) {
        return new IAntelliService.Stub() {
            @Override
            public boolean canAnswer(Question question) throws RemoteException {
                return AntelliService.this.canAnswer(question);
            }

            @Override
            public Answer answer(Question question) throws RemoteException {
                return AntelliService.this.answer(question);
            }

            @Override
            public boolean canAnswerCommand(Uri command) throws RemoteException {
                return AntelliService.this.canAnswerCommand(command);
            }

            @Override
            public Answer answerCommand(Uri command) throws RemoteException {
                return AntelliService.this.answerCommand(command);
            }

            @Override
            public void showSettings() throws RemoteException {
                AntelliService.this.showSettings();
            }

            @Override
            public void reset() throws RemoteException{
                AntelliService.this.reset();
            }
        };
    }
}
