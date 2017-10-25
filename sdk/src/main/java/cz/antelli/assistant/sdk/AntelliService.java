package cz.antelli.assistant.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import cz.antelli.assistant.sdk.model.Answer;
import cz.antelli.assistant.sdk.model.Question;
import cz.antelli.assistant.sdk.model.ServiceMeta;

/**
 * Created by stepan on 29.08.2017.
 */

public abstract class AntelliService extends Service {

    public static final String ACTION_ANSWER = "cz.antelli.assistant.ANSWER";

    public abstract ServiceMeta getServiceMeta() throws RemoteException;
    public abstract boolean canAnswer(Question question) throws RemoteException;
    public abstract Answer answer(Question question) throws RemoteException;
    protected void reset(){}

    public boolean canAnswerCommand(String command) throws RemoteException{
        return false;
    }
    public Answer answerCommand(String command) throws RemoteException{
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
            public boolean canAnswerCommand(String command) throws RemoteException {
                return AntelliService.this.canAnswerCommand(command);
            }

            @Override
            public Answer answerCommand(String command) throws RemoteException {
                return AntelliService.this.answerCommand(command);
            }

            @Override
            public ServiceMeta getServiceMeta() throws RemoteException {
                ServiceMeta meta =  AntelliService.this.getServiceMeta();
                if (meta == null){
                    throw new RuntimeException("Service meta is null, implement getServiceMetaData() method.");
                }
                else {
                    if (meta.getServiceName() == null){
                        throw new RuntimeException("ServiceMeta doesn't contains name.");
                    }
                    if (meta.getAuthor() == null){
                        throw new RuntimeException("ServiceMeta doesn't contains author.");
                    }
                    if (meta.getDescription() == null){
                        throw new RuntimeException("ServiceMeta doesn't contains description.");
                    }
                }
                if (meta.getServiceId() == null){
                    meta.setServiceId(getPackageName());
                }
                return meta;
            }

            @Override
            public int[] getSdkVersion() throws RemoteException {
                return AntelliSDK.getApiVersion();
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
