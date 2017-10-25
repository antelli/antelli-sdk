// IAntelliService.aidl
package cz.antelli.assistant.sdk;
import cz.antelli.assistant.sdk.model.Answer;
import cz.antelli.assistant.sdk.model.AnswerItem;
import cz.antelli.assistant.sdk.model.Question;
import cz.antelli.assistant.sdk.model.ServiceMeta;

// Declare any non-default types here with import statements

interface IAntelliService {
    int[] getSdkVersion();
    ServiceMeta getServiceMeta();
    boolean canAnswer(in Question question);
    Answer answer(in Question question);
    boolean canAnswerCommand(in String command);
    Answer answerCommand(in String command);
    void reset();
    void showSettings();
}
