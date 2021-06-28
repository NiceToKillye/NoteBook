package loader.controller;

import loader.exception.*;
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
            message = "email was taken";
        }
        else if(exception instanceof LoginWasTakenException){
            message = "login was taken";
        }
        else if(exception instanceof NullPointerException){
            message = "all field must be filled";
        }
        else if(exception instanceof WrongEmailException){
            message = "wrong email";
        }

        model.addAttribute("show", true);
        model.addAttribute("errorMessage", message);
        return "registration";
    }
}