package loader.controller;

import loader.entitie.RegistrationRequest;
import loader.exception.EmailWasTakenException;
import loader.exception.LoginWasTakenException;
import loader.exception.WrongEmailException;
import loader.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    private String register(RegistrationRequest registrationRequest)
            throws
            LoginWasTakenException,
            EmailWasTakenException,
            WrongEmailException {
        registrationService.register(registrationRequest);
        return "login";
    }
}
