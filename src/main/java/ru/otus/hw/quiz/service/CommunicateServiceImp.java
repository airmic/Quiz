package ru.otus.hw.quiz.service;

import java.util.Scanner;

public class CommunicateServiceImp implements  CommunicateService{

    private Scanner scan;

    public void csInit() {
        scan = new Scanner(System.in);
    }

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
