package io.antelli.sdk;
import io.antelli.sdk.model.Answer;
import io.antelli.sdk.model.AnswerItem;
import io.antelli.sdk.model.Question;

interface IAntelliPlugin {
    boolean canAnswer(in Question question);
    Answer answer(in Question question);
    Answer command(in String command);
    void reset();
    void showSettings();
}
