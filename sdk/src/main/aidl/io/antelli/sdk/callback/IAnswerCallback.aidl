package io.antelli.sdk.callback;
import io.antelli.sdk.model.Answer;

interface IAnswerCallback {
    void publish(in Answer answer);
}