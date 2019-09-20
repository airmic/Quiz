package ru.otus.hw.quiz.service;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Locale;
import java.util.Scanner;

public class CommunicateServiceImp implements  CommunicateService{

    private Scanner scan;

    private ReloadableResourceBundleMessageSource bundleMessageSource;
    private Locale locale;

    @PostConstruct
    public void csInit() {
        scan = new Scanner(System.in);
        bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setBasename("/i18n/quiestion_service");
        locale = Locale.ENGLISH;
    }

    public void setLocale(String langStr) {
        if( "RU".equals(langStr.toUpperCase()) )
            locale = new Locale("ru");
        else
            locale = Locale.ENGLISH;
    }

    @Override
    public String getLangname() {
        return locale.toString();
    }

    @PreDestroy
    public void csDestroy() {
        scan.close();
    }

    @Override
    public void putI10nCode(String str) {
        putI10nCode(str, null);
    }

    @Override
    public void putI10nCode(String str, Object[] objs) {
        System.out.println(bundleMessageSource.getMessage(str, objs, locale));
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
