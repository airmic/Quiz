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
        CommunicateService communicateService = context.getBean(CommunicateService.class);
        service.executeQuiz();
        communicateService.putEmptyLines(1);
        communicateService.putI10nCode("quiz.finished");
    }


}
