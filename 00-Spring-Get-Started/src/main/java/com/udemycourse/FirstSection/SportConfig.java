package com.udemycourse.FirstSection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    
    @Bean("InjectingBeanId")
    public Coach footballCoach() {
        return new FootballCoach("Hello from bean");
    }
}
