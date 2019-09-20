package ru.otus.hw.quiz.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw.quiz.domain.Answer;
import ru.otus.hw.quiz.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerDaoCsv implements AnswerDao {

    private  final String filename;

    public AnswerDaoCsv(String filename) {
        this.filename = filename;
    }

    private List<Answer> answerList = new ArrayList<>();

    @Override
    public List<Answer> getAnswerList() {
        return answerList;
    }

    @Override
    public void setLang(String lang)  throws IOException {
        String locFilename = filename.replaceFirst("@LANG@", lang);
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(locFilename)));

        String csvLine;
        String[] splitedCsvLine;
        while ((csvLine = br.readLine()) != null) {
            splitedCsvLine = csvLine.split(";");
            if (splitedCsvLine.length > 2) {
                answerList.add(new Answer(new Question(splitedCsvLine[0], splitedCsvLine[1], Arrays.asList(Arrays.copyOfRange(splitedCsvLine,2, splitedCsvLine.length)))));
            }
        }
    }
}
