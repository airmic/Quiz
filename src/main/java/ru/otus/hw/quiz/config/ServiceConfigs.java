package ru.otus.hw.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw.quiz.dao.AnswerDao;
import ru.otus.hw.quiz.dao.AnswerDaoCsv;
import ru.otus.hw.quiz.service.CommunicateService;
import ru.otus.hw.quiz.service.CommunicateServiceImp;

import java.io.IOException;

@Configuration
@PropertySource("application.properties")
public class ServiceConfigs {
    @Bean
    public CommunicateService communicateService() {
        return new CommunicateServiceImp();
    }

    @Bean
    AnswerDao answerDao(@Value("${question.resource}") String filename) throws IOException {
        return new AnswerDaoCsv(filename);
    }
}
