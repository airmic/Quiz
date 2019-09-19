package ru.otus.hw.quiz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Answer {
    private final Question question;
    private boolean checkedResult;

    public void checkAnswer(String answer) {
        checkedResult = answer.equals(question.getTrueAnswer());
    }

}
