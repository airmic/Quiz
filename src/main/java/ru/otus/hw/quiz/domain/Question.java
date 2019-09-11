package ru.otus.hw.quiz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Question {
    private final String question;
    private final String trueAnswer;
    private final List<String> answerList;
    private Boolean isTrue;

    public void checkAnswer(String answer) {
        isTrue = trueAnswer.equals(answer) ? true : false;
    }

    public void checkAnswer(Integer idx) {
        isTrue = trueAnswer.equals(answerList.get(idx)) ? true : false;
    }

}
