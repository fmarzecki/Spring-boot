package com.udemycourse.FirstSection;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Training Cricket";
    }

}
