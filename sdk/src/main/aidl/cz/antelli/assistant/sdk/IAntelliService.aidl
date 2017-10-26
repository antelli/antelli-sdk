// IAntelliService.aidl
package cz.antelli.assistant.sdk;
import cz.antelli.assistant.sdk.model.Answer;
import cz.antelli.assistant.sdk.model.AnswerItem;
import cz.antelli.assistant.sdk.model.Question;
import android.net.Uri;

// Declare any non-default types here with import statements

interface IAntelliService {
    boolean canAnswer(in Question question);
    Answer answer(in Question question);
    boolean canAnswerCommand(in Uri command);
    Answer answerCommand(in Uri command);
    void reset();
    void showSettings();
}
