package ru.otus.hw.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw.quiz.service.CommunicateService;
import ru.otus.hw.quiz.service.CommunicateServiceImp;
import ru.otus.hw.quiz.service.QuestionService;
import ru.otus.hw.quiz.service.QuestionServiceImp;

@Configuration
public class ServiceConfigs {
//    @Bean
//    public QuestionService questionService() {
//        return new QuestionServiceImp();
//
//    }
    @Bean
    public CommunicateService communicateService() {
        return new CommunicateServiceImp();
    }
}
