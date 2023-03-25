package com.udemycourse.FirstSection;

// import org.springframework.stereotype.Component;

public class FootballCoach implements Coach {
    public String message;

    public FootballCoach(String msg) {
        message = msg;
    }

    @Override
    public String getDailyWorkout() {
        return message;
    }
}
