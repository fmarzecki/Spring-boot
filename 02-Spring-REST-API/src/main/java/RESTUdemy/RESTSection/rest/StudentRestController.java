package RESTUdemy.RESTSection.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RESTUdemy.RESTSection.entity.Student;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;


    //podczas tworzenia Beana za pomoca @RestController przy okazji wywola te funkcje tylko raz
    @PostConstruct
    public void loadStudents() {
        students = new ArrayList<>();
        
        students.add(new Student("Filip", "Marzecki"));
        students.add(new Student("Ela", "Lipa"));
        students.add(new Student("Dua", "Zgrzyta"));
    }

    @GetMapping("/students")
    public List<Student> helloWorld() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if ( (studentId >= students.size() || studentId <0 )) {
            throw new StudentNotFoundException("Student not found - id: " + studentId);
        }
        return students.get(studentId);
    }


}
