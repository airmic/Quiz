package ru.otus.hw.quiz.service;


import java.util.Locale;

public interface CommunicateService {
    void putI10nCode(String str);
    void putI10nCode(String str, Object[] objs);
    void putString(String str);
    void putEmptyLines(int num);
    Locale getLocale();

    String getObject();

    void inputLocale();
}
