package cz.antelli.sdk;
import cz.antelli.sdk.model.Answer;
import cz.antelli.sdk.model.AnswerItem;
import cz.antelli.sdk.model.Question;

interface IAntelliPlugin {
    boolean canAnswer(in Question question);
    Answer answer(in Question question);
    Answer command(in String command);
    void reset();
    void showSettings();
}
