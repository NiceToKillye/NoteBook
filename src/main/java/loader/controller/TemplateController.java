package loader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String loadLogin(){
        return "login";
    }

    @GetMapping("recovery")
    public String loadRecovery(Model model){
        model.addAttribute("show", false);
        return "recovery";
    }

    @GetMapping("registration")
    public String loadRegistration(Model model){
        model.addAttribute("show", false);
        return "registration";
    }
}
