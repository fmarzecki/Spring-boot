package com.udemycourse.FirstSection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
    
    @Override
    public String getDailyWorkout() {
        return "Messi's training";
    }
}
