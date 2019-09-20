package ru.otus.hw.quiz.dao;

import ru.otus.hw.quiz.domain.Answer;

import java.io.IOException;
import java.util.List;

public interface AnswerDao {
    List<Answer> getAnswerList();
    void setLang(String lang)  throws IOException;
}
