package RESTUdemy.RESTSection.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//ControlerAdvice makes our ExceptionHandlers globally available, not only within the @RestController scope
@ControllerAdvice
public class StudentRestExceptionHandler {

    // Dodaje ExceptionHandler dla StudentNotFoundException ktory zwroci ResponseEntity<StudentErrorMessage>
    @ExceptionHandler
    public ResponseEntity<StudentErrorMessage> handleException(StudentNotFoundException exc) {
        StudentErrorMessage error = new StudentErrorMessage();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // pierwszy argument to cialo, a drugi status tej odpowiedzi
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Dodaje nowy ExceptionHandler dla wszystkich Exceptions a nie tylko StudentNotFound
    @ExceptionHandler
    public ResponseEntity<StudentErrorMessage> handleException(Exception exc) {
        StudentErrorMessage error = new StudentErrorMessage();

        //BAD_REQUEST poniewaz ktos zada od nas stringa a nie integer do znalezienia ID studenta
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // pierwszy argument to cialo, a drugi status tej odpowiedzi
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

