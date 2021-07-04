package loader.controller;

import loader.exception.WrongEmailException;
import loader.exception.WrongRecoveryEmailException;
import loader.service.RecoveryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recovery")
public class RecoveryController {

    private final RecoveryService service;

    public RecoveryController(RecoveryService service) {
        this.service = service;
    }

    @PostMapping
    public String recovery(String email) throws WrongRecoveryEmailException {
        service.sendPassword(email);
        return "login";
    }
}
