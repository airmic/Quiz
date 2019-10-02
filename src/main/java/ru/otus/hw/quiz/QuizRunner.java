package ru.otus.hw.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import ru.otus.hw.quiz.service.CommunicateService;
import ru.otus.hw.quiz.service.QuestionService;

@Configuration
@ComponentScan
public class QuizRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizRunner.class);
        QuestionService service = context.getBean(QuestionService.class);
        service.executeQuiz();
    }


}
