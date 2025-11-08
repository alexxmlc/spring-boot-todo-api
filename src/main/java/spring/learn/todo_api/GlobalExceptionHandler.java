package spring.learn.todo_api;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Tells spring that this class handles exception from all the controller clases
public class GlobalExceptionHandler {
    
    //Spring will run this method if a MethodArgumentNotValidException happens 
    //This is what @Valid throws if it fails
    @ExceptionHandler(MethodArgumentNotValidException.class) 
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Sets the HTTP status to 400

    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){

        //Map to hold the errors
        Map<String, String> errors = new HashMap<>();
        
        //Loop over all the errors, get the name and the error message
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
    
}
