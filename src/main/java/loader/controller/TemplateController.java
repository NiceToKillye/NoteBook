package loader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("recovery")
    public String loadRecovery(){
        return "recovery";
    }

    @GetMapping("login")
    public String loadLogin(){
        return "login";
    }

    @GetMapping("registration")
    public String loadRegistration(){
        return "registration";
    }
}