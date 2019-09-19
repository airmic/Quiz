package ru.otus.hw.quiz.dao;

import ru.otus.hw.quiz.domain.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> getAnswerList();
}
