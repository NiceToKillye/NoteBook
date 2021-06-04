package loader.controller;

import loader.exception.NoteNotFoundException;
import loader.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({NoteNotFoundException.class, UserNotFoundException.class})
    public final ResponseEntity<String> handleException(Exception ex) {
        if(ex instanceof NoteNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NoteNotFoundException notFoundEx = (NoteNotFoundException) ex;
            return ResponseEntity.status(status).body("Note with id: " + notFoundEx.getMessage() + " was not found");
        }
        else if(ex instanceof UserNotFoundException){
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException notFoundEx = (UserNotFoundException) ex;
            return ResponseEntity.status(status).body("User with this login: " + notFoundEx.getMessage() + " was not found");
        }
        else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body("Something went wrong");
        }
    }
}