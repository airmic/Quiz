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
import ru.otus.hw.quiz.service.CommunicateService;
import ru.otus.hw.quiz.service.CommunicateServiceImp;



import static java.util.Locale.*;

@Configuration
@PropertySource("application.properties")
public class ServiceConfigs {
    @Bean
    @Autowired
    public CommunicateService communicateService(MessageSource messageSource, ConsoleContext consoleContext) {
        return new CommunicateServiceImp(messageSource, consoleContext);
    }

    @Bean
    @Autowired
    public AnswerDao answerDao(@Value("${question.resource}") String filename, ConsoleContext consoleContext) {
        return new AnswerDaoCsv(filename, consoleContext);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setBasename("/i18n/quiestion_service");
        return bundleMessageSource;
    }

    @Bean
    public ConsoleContext consoleContext() {
        return new ConsoleContext(System.in, System.out);
    }

}
