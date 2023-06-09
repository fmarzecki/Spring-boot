package com.udemycourse.FirstSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    private Coach workout;

    @Autowired
    private FunRestController(@Qualifier("InjectingBeanId") Coach workout){
        this.workout = workout;
    }

    @GetMapping("/")
    public String getDailyWorkout() {
        return  workout.getDailyWorkout();
    }

}
