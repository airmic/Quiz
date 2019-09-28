package ru.otus.hw.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw.quiz.config.Consts;
import ru.otus.hw.quiz.dao.AnswerDao;
import ru.otus.hw.quiz.domain.Answer;


@Component
public class QuestionServiceImp implements QuestionService{

    private AnswerDao dao;
    private CommunicateService communicateService;


    @Autowired
    public QuestionServiceImp(AnswerDao doa, CommunicateService communicateService) {
        this.dao = doa;
        this.communicateService = communicateService;
    }

    private void languageSelect() {
        communicateService.putI10nCode(Consts.SELECT_LANGUAGE);
        communicateService.inputLocale();
    }

    private void showQuestion(String question) {
        communicateService.putEmptyLines(1);
        communicateService.putString(question);
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.INPUT_OPTIONS);
    }

    private void showAnswerVariants(Answer answer) {
        for (String vanswer : answer.getQuestion().getAnswerList()) {
            communicateService.putString(vanswer);
        }
    }

    private void inputAnswer(Answer answer) {
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.INPUT_ANSWER);
        answer.checkAnswer(communicateService.getObject());
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(answer.isCheckedResult() ? Consts.ANSWER_RIGTH : Consts.ANSWER_WRONG);
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
        communicateService.putI10nCode(Consts.INPUT_RESULT);
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.ANSWER_CNT_RIGHT, new Object[] {trueCnt});
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.ANSWER_CNT_WRONG, new Object[] {falseCnt});

        communicateService.putEmptyLines(1);
        communicateService.putI10nCode(Consts.QUIZ_FINISHED);

    }

    public void executeQuiz() {

        languageSelect();
        executeLanguagedQuiz();
        runReport();
    }
}
