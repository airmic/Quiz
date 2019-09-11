package ru.otus.hw.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw.quiz.service.QuestionService;

public class QuizRunner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("quiz-context.xml");
//        QuestionService service = (QuestionService) context.getBean("questionService");
        QuestionService service = context.getBean(QuestionService.class);
        service.executeQuiz();
        System.out.println("\n\nQuiz finished");
    }
}
