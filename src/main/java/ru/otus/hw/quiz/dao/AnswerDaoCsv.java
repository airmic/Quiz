package ru.otus.hw.quiz.dao;

import ru.otus.hw.quiz.config.ConsoleContext;
import ru.otus.hw.quiz.config.QuizSettings;
import ru.otus.hw.quiz.domain.Answer;
import ru.otus.hw.quiz.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AnswerDaoCsv implements AnswerDao {

    private List<Answer> answerList;
    private final ConsoleContext consoleContext;
    private final QuizSettings quizSettings;

    public AnswerDaoCsv(QuizSettings quizSettings, ConsoleContext consoleContext) {
        this.quizSettings = quizSettings;
        this.consoleContext = consoleContext;
        answerList = new ArrayList<>();

    }


    @Override
    public List<Answer> getAnswerList() {
        if( answerList.isEmpty() )
            reloadQuiz();
        return answerList;
    }


    public void reloadQuiz() {

        answerList.clear();

        String locFilename = String.format(quizSettings.getFileNameTemplate(), quizSettings.getLocale().getLanguage());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(locFilename)))) {

            String csvLine;
            String[] splitedCsvLine;
            while ((csvLine = br.readLine()) != null) {
                splitedCsvLine = csvLine.split(";");
                if (splitedCsvLine.length > 2) {
                    answerList.add(new Answer(new Question(splitedCsvLine[0], splitedCsvLine[1], Arrays.asList(Arrays.copyOfRange(splitedCsvLine, 2, splitedCsvLine.length)))));
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Quiz file read error");
        }
    }


}
