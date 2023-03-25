package RESTUdemy.RESTSection.rest;

public class StudentNotFoundException extends RuntimeException {
    
    public StudentNotFoundException(String message){
        super(message);
    }
}
