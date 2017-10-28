// IAntelliService.aidl
package cz.antelli.sdk;
import cz.antelli.sdk.model.Answer;
import cz.antelli.sdk.model.AnswerItem;
import cz.antelli.sdk.model.Question;

// Declare any non-default types here with import statements

interface IAntelliPlugin {
    boolean canAnswer(in Question question);
    Answer answer(in Question question);
    Answer answerCommand(in String command);
    void reset();
    void showSettings();
}
