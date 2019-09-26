package ru.otus.hw.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw.quiz.config.Consts;
import ru.otus.hw.quiz.dao.AnswerDao;
import ru.otus.hw.quiz.domain.Answer;
import ru.otus.hw.quiz.domain.QuizLanguage;

import java.io.IOException;
import java.util.Locale;


@Component
public class QuestionServiceImp implements QuestionService{

    private AnswerDao dao;
    private CommunicateService communicateService;
    private QuizLanguage quizLanguage;


    @Autowired
    public QuestionServiceImp(AnswerDao doa, CommunicateService communicateService, QuizLanguage quizLanguage) {
        this.dao = doa;
        this.communicateService = communicateService;
        this.quizLanguage = quizLanguage;
    }

    private void languageSelect() {
        communicateService.putI10nCode(Consts.selectLanguage);
        quizLanguage.setLocale( new Locale(communicateService.getObject()) );

        try {
            dao.setLang(quizLanguage.getLanguage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showQuestion(String question) {
        communicateService.putEmptyLines(1);
        communicateService.putString(question);
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.inputOptions);
    }

    private void showAnswerVariants(Answer answer) {
        for (String vanswer : answer.getQuestion().getAnswerList()) {
            communicateService.putString(vanswer);
        }
    }

    private void inputAnswer(Answer answer) {
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.inputAnswer);
        answer.checkAnswer(communicateService.getObject());
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(answer.isCheckedResult() ? Consts.answerRight : Consts.answerWrong);
    }

    private void executeLanguagedQuiz() {
        for (Answer answer : dao.getAnswerList()) {
            showQuestion(answer.getQuestion().getQuestion());
            showAnswerVariants(answer);
            inputAnswer(answer);
        }
    }

    private void runReport() {

        int trueCnt=dao.getAnswerList().stream().mapToInt(s -> s.isCheckedResult()?1:0).sum();
        int falseCnt=dao.getAnswerList().size()-trueCnt;

        communicateService.putEmptyLines(2);
        communicateService.putI10nCode(Consts.inputResult);
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.answerCntRight, new Object[] {trueCnt});
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.answerCntWrong, new Object[] {falseCnt});

        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.quizFinished);

    }

    public void executeQuiz() {

        languageSelect();
        executeLanguagedQuiz();
        runReport();
    }
}
