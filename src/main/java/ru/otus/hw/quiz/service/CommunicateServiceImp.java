package ru.otus.hw.quiz.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Scanner;

public class CommunicateServiceImp implements  CommunicateService{

    private Scanner scan;

    @PostConstruct
    public void csInit() {
        scan = new Scanner(System.in);
    }

    @PreDestroy
    public void csDestroy() {
        scan.close();
    }

    @Override
    public void putString(String str) {
        System.out.println(str);
    }

    @Override
    public String getObject() {
        return scan.next();
    }

}
