package ru.otus.hw.quiz.service;

import ru.otus.hw.quiz.dao.QuestionDao;
import ru.otus.hw.quiz.domain.Question;

import java.util.Scanner;

public class QuestionServiceImp implements QuestionService{

    private QuestionDao dao;

    public QuestionServiceImp(QuestionDao doa) {
        this.dao = doa;
    }

    @Override
    public void executeQuiz() {

        try (Scanner scan = new Scanner(System.in) ) {
            for (Question question : dao.getQuestionList()) {
                System.out.println("\n\n"+question.getQuestion() + "\n\n\n Варианты:\n");

                for (String answer : question.getAnswerList()) {
                    System.out.println(answer);
                }
                System.out.println("\nВведите ответ: ");
                question.checkAnswer(scan.next());
                System.out.println("\n"+(question.getIsTrue() ? "Правильно" : "Неправильно"));
            }

            Byte trueCnt=0;
            Byte falseCnt=0;
            for(Question question : dao.getQuestionList()) {
                if (question.getIsTrue() )
                    trueCnt++;
                else
                    falseCnt++ ;
            }
            System.out.println(String.format("\n\n Результат:\nправильных ответов - %d\nнеправильных ответов- %d", trueCnt, falseCnt));
        }

    }
}
