package ru.otus.hw.quiz.service;

import ru.otus.hw.quiz.dao.AnswerDao;
import ru.otus.hw.quiz.domain.Answer;

public class QuestionServiceImp implements QuestionService{

    private AnswerDao dao;
    private CommunicateService communicateService;

    public QuestionServiceImp(AnswerDao doa, CommunicateService communicateService) {
        this.dao = doa;
        this.communicateService = communicateService;
    }

    @Override
    public void executeQuiz() {

            for (Answer answer : dao.getAnswerList()) {
                communicateService.putString("\n\n"+answer.getQuestion().getQuestion() + "\n\n\n Варианты:\n");

                for (String vanswer : answer.getQuestion().getAnswerList()) {
                    communicateService.putString(vanswer);
                }
                communicateService.putString("\nВведите ответ: ");
                answer.checkAnswer(communicateService.getObject());
                communicateService.putString("\n"+(answer.isCheckedResult() ? "Правильно" : "Неправильно"));
            }


            int trueCnt=dao.getAnswerList().stream().mapToInt(s -> s.isCheckedResult()?1:0).sum();
            int falseCnt=dao.getAnswerList().size()-trueCnt;

        communicateService.putString(String.format("\n\n Результат:\nправильных ответов - %d\nнеправильных ответов- %d", trueCnt, falseCnt) );

    }
}
