package ru.otus.hw.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw.quiz.service.CommunicateService;
import ru.otus.hw.quiz.service.QuestionService;

public class QuizRunner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("quiz-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        CommunicateService communicateService = context.getBean(CommunicateService.class);
        service.executeQuiz();
        communicateService.putString("\n\nQuiz finished");
    }
}
