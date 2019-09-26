package ru.otus.hw.quiz.service;

import org.springframework.context.MessageSource;
import ru.otus.hw.quiz.domain.QuizLanguage;

import java.util.Locale;
import java.util.Scanner;

public class CommunicateServiceImp implements  CommunicateService{

    private Scanner scan;

    private MessageSource messageSource;
    private QuizLanguage quizLanguage;

    public CommunicateServiceImp(MessageSource messageSource, Scanner scan, QuizLanguage quizLanguage) {
        this.scan = scan;
        this.messageSource = messageSource;
        this.quizLanguage = quizLanguage;
    }

    @Override
    public void putI10nCode(String str) {
        putI10nCode(str, null);
    }

    @Override
    public void putI10nCode(String str, Object[] objs) {
        System.out.println(messageSource.getMessage(str, objs, quizLanguage.getLocale()));
    }

    @Override
    public void putString(String str) {
        System.out.println(str);
    }

    @Override
    public void putEmptyLines(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<num; i++)
            sb.append("\n");
        System.out.println(sb.toString());
    }

    @Override
    public String getObject() {
        return scan.next();
    }

}
