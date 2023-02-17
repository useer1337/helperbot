package com.example.helperbot.configuration;

import com.example.helperbot.annotations.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider() {
        var scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Command.class));
        return scanner;
    }
}
