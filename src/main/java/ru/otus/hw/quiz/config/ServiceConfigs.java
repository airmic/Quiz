package ru.otus.hw.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw.quiz.dao.AnswerDao;
import ru.otus.hw.quiz.dao.AnswerDaoCsv;
import ru.otus.hw.quiz.domain.QuizLanguage;
import ru.otus.hw.quiz.service.CommunicateService;
import ru.otus.hw.quiz.service.CommunicateServiceImp;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import static java.util.Locale.*;

@Configuration
@PropertySource("application.properties")
public class ServiceConfigs {
    @Bean
    @Autowired
    public CommunicateService communicateService(MessageSource messageSource, Scanner scan, QuizLanguage quizLanguage) {
        return new CommunicateServiceImp(messageSource, scan, quizLanguage);
    }

    @Bean
    public AnswerDao answerDao(@Value("${question.resource}") String filename) throws IOException {
        return new AnswerDaoCsv(filename);
    }

    @Bean
    public MessageSource messageSource() {
        MessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
        ((ReloadableResourceBundleMessageSource)bundleMessageSource).setDefaultEncoding("UTF-8");
        ((ReloadableResourceBundleMessageSource)bundleMessageSource).setBasename("/i18n/quiestion_service");
        return bundleMessageSource;
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public QuizLanguage quizLanguage() {
        return new QuizLanguage(new Locale(ENGLISH.getLanguage()));
    }
}
