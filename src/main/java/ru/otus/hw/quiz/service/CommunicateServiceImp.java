package ru.otus.hw.quiz.service;

import org.springframework.context.MessageSource;
import ru.otus.hw.quiz.config.ConsoleContext;
import ru.otus.hw.quiz.config.QuizSettings;

import java.util.Locale;

public class CommunicateServiceImp implements  CommunicateService{

    private final ConsoleContext consoleContext;
    private final MessageSource messageSource;
    private final QuizSettings quizSettings;

    public CommunicateServiceImp(MessageSource messageSource, ConsoleContext consoleContext, QuizSettings quizSettings) {
        this.consoleContext = consoleContext;
        this.messageSource = messageSource;
        this.quizSettings = quizSettings;
    }

    @Override
    public void putI10nCode(String str) {
        putI10nCode(str, null);
    }

    @Override
    public void putI10nCode(String str, Object[] objs) {
        consoleContext.getPrintStream().println(messageSource.getMessage(str, objs, quizSettings.getLocale()));
    }

    @Override
    public void putString(String str) {
        consoleContext.getPrintStream().println(str);
    }

    @Override
    public void putEmptyLines(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<num; i++)
            sb.append("\n");
        consoleContext.getPrintStream().println(sb.toString());
    }


    @Override
    public String getObject() {
        return consoleContext.next();
    }

    @Override
    public void inputLocale() {
        quizSettings.setLocale(new Locale(getObject()));

    }

}
