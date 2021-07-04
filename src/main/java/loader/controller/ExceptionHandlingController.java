package loader.controller;

import loader.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({
            EmailWasTakenException.class,
            LoginWasTakenException.class,
            NullPointerException.class,
            WrongEmailException.class
    })
    public String register(Model model, Exception exception){
        String message = "Something went wrong";

        if(exception instanceof EmailWasTakenException){
            message = "Email was taken";
        }
        else if(exception instanceof LoginWasTakenException){
            message = "Login was taken";
        }
        else if(exception instanceof NullPointerException){
            message = "All field must be filled";
        }
        else if(exception instanceof WrongEmailException){
            message = "Wrong email";
        }

        model.addAttribute("show", true);
        model.addAttribute("errorMessage", message);
        return "registration";
    }

    @ExceptionHandler({
            WrongRecoveryEmailException.class,
            UsernameNotFoundException.class
    })
    public String recovery(Model model, Exception exception){
        String message = "Something went wrong!";
        if(exception instanceof WrongRecoveryEmailException){
            message = "Wrong email!";
        }
        else if(exception instanceof UsernameNotFoundException){
            message = "Username with this email was not found!";
        }

        model.addAttribute("show", true);
        model.addAttribute("errorMessage", message);
        return "recovery";
    }

    @ExceptionHandler({
            EmptyNoteException.class
    })
    public ResponseEntity<String> emptyNote(Exception exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({
            NoteNotFoundException.class
    })
    public ResponseEntity<String> deleteNote(Exception exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}