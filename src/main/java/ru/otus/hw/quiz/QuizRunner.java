package ru.otus.hw.quiz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import ru.otus.hw.quiz.dao.AnswerDao;
import ru.otus.hw.quiz.dao.AnswerDaoCsv;
import ru.otus.hw.quiz.service.CommunicateService;
import ru.otus.hw.quiz.service.QuestionService;

import java.io.IOException;

@Configuration
@ComponentScan
@PropertySource("application.properties")
public class QuizRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizRunner.class);
        QuestionService service = context.getBean(QuestionService.class);
        CommunicateService communicateService = context.getBean(CommunicateService.class);
        service.executeQuiz();
        communicateService.putString("\n\nQuiz finished");
    }

    @Bean
    AnswerDao answerDao(@Value("${question.resource}") String filename) throws IOException {
        return new AnswerDaoCsv(filename);
    }
}
