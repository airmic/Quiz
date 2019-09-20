package ru.otus.hw.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw.quiz.dao.AnswerDao;
import ru.otus.hw.quiz.domain.Answer;

import java.io.IOException;
import java.util.Objects;


@Component
public class QuestionServiceImp implements QuestionService{

    private AnswerDao dao;
    private CommunicateService communicateService;

    @Autowired
    public QuestionServiceImp(AnswerDao doa, CommunicateService communicateService) {
        this.dao = doa;
        this.communicateService = communicateService;
    }

    public void executeQuiz() {

        communicateService.putI10nCode("select.language");
        communicateService.setLocale( communicateService.getObject() );

        try {
            dao.setLang(communicateService.getLangname());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (Answer answer : dao.getAnswerList()) {
            communicateService.putEmptyLines(1);
            communicateService.putString(answer.getQuestion().getQuestion());
            communicateService.putEmptyLines(1);
            communicateService.putI10nCode("input.options");

            for (String vanswer : answer.getQuestion().getAnswerList()) {
                communicateService.putString(vanswer);
            }
            communicateService.putEmptyLines(1);
            communicateService.putI10nCode("input.answer");
            answer.checkAnswer(communicateService.getObject());
            communicateService.putEmptyLines(1);
            communicateService.putI10nCode(answer.isCheckedResult() ? "answer.rigth" : "answer.wrong");
        }


        int trueCnt=dao.getAnswerList().stream().mapToInt(s -> s.isCheckedResult()?1:0).sum();
        int falseCnt=dao.getAnswerList().size()-trueCnt;

        communicateService.putEmptyLines(2);
        communicateService.putI10nCode("input.result");
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode("answer.cnt.rigth",new Object[] {trueCnt});
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode("answer.cnt.wrong",new Object[] {falseCnt});

    }
}
