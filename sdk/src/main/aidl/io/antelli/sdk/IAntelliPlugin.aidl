package io.antelli.sdk;
import io.antelli.sdk.model.Answer;
import io.antelli.sdk.model.AnswerItem;
import io.antelli.sdk.model.Question;
import io.antelli.sdk.model.Command;
import io.antelli.sdk.callback.ICanAnswerCallback;
import io.antelli.sdk.callback.IAnswerCallback;

interface IAntelliPlugin {
    oneway void canAnswer(in Question question, ICanAnswerCallback callback);
    oneway void answer(in Question question, IAnswerCallback callback);
    oneway void command(in Command command, IAnswerCallback callback);
    void reset();
    void showSettings();
}
