package io.antelli.sdk;
import io.antelli.sdk.model.Answer;
import io.antelli.sdk.model.AnswerItem;
import io.antelli.sdk.model.Question;
import io.antelli.sdk.model.Command;
import io.antelli.sdk.callback.IAnswerCallback;

interface IAntelliPlugin {
    boolean canAnswer(in Question question);
    oneway void answer(in Question question, IAnswerCallback callback);
    oneway void command(in Command command, IAnswerCallback callback);
    void reset();
    void showSettings();
}
