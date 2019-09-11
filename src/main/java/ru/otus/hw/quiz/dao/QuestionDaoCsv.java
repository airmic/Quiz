package ru.otus.hw.quiz.dao;

import ru.otus.hw.quiz.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDaoCsv implements QuestionDao{

    private List<Question> questionList = new ArrayList<Question>();

    public QuestionDaoCsv(String filename) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)));

        String csvLine;
        String[] splitedCsvLine;
        while ((csvLine = br.readLine()) != null) {
            splitedCsvLine = csvLine.split(";");
            if (splitedCsvLine.length > 2) {
                questionList.add(new Question(splitedCsvLine[0], splitedCsvLine[1], Arrays.asList(Arrays.copyOfRange(splitedCsvLine,2, splitedCsvLine.length))));
            }
        }
    }


    @Override
    public List<Question> getQuestionList() {
        return questionList;
    }
}
