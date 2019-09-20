package ru.otus.hw.quiz.service;


public interface CommunicateService {
    void putI10nCode(String str);
    void putI10nCode(String str, Object[] objs);
    void putString(String str);
    void putEmptyLines(int num);
    void setLocale(String langStr);
    String getLangname();
    String getObject();
}
