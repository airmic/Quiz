package ru.otus.hw.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;

@Data
@AllArgsConstructor
public class QuizLanguage {
    Locale locale;

    public String getLanguage() {
        return locale.getLanguage();
    }
}
