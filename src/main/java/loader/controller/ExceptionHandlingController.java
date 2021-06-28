package loader.controller;

import loader.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(
            {
                    NoteNotFoundException.class,
                    UserNotFoundException.class,
                    EmailWasTakenException.class,
                    LoginWasTakenException.class,
                    NullPointerException.class,
                    WrongEmailException.class
            })
    public final ResponseEntity<String> handleException(Exception ex) {
        HttpStatus status;
        if(ex instanceof NoteNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            NoteNotFoundException notFoundEx = (NoteNotFoundException) ex;
            return ResponseEntity.status(status).body(notFoundEx.getMessage());
        }
        else if(ex instanceof UsernameNotFoundException){
            status = HttpStatus.NOT_FOUND;
            UsernameNotFoundException notFoundEx = (UsernameNotFoundException) ex;
            return ResponseEntity.status(status).body("User with this login: " + notFoundEx.getMessage() + " was not found");
        }
        else if(ex instanceof EmailWasTakenException ){
            status = HttpStatus.CONFLICT;
            EmailWasTakenException takenEx = (EmailWasTakenException) ex;
            return ResponseEntity.status(status).body(takenEx.getMessage());
        }
        else if(ex instanceof LoginWasTakenException){
            status = HttpStatus.CONFLICT;
            LoginWasTakenException takenEx = (LoginWasTakenException) ex;
            return ResponseEntity.status(status).body(takenEx.getMessage());
        }
        else if(ex instanceof NullPointerException){
            status = HttpStatus.CONFLICT;
            NullPointerException emptyException = (NullPointerException) ex;
            return ResponseEntity.status(status).body(emptyException.getMessage());
        }
        else if (ex instanceof WrongEmailException){
            status = HttpStatus.CONFLICT;
            WrongEmailException wrongEmail = (WrongEmailException) ex;
            return ResponseEntity.status(status).body(wrongEmail.getMessage());
        }
        else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body("Something went wrong");
        }
    }
}