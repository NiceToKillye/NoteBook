package loader.service;

import loader.entitie.RegistrationRequest;
import loader.entitie.User;
import loader.entitie.UserRepository;
import loader.exception.EmailWasTakenException;
import loader.exception.LoginWasTakenException;
import loader.exception.WrongEmailException;
import loader.security.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegistrationService {

    private final String EMAIL = "\\w+@\\w+\\.[a-z]+";
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegistrationRequest registrationRequest)
            throws
            EmailWasTakenException,
            LoginWasTakenException,
            WrongEmailException {

        if(repository.findUserByLogin(registrationRequest.getLogin()).isPresent()){
            throw new LoginWasTakenException(registrationRequest.getLogin());
        }
        else if (repository.findUserByEmail(registrationRequest.getEmail()).isPresent()){
            throw new EmailWasTakenException(registrationRequest.getEmail());
        }
        else if(registrationRequest.getEmail().isEmpty() ||
                registrationRequest.getLogin().isEmpty() ||
                registrationRequest.getPassword().isEmpty()){
            throw new NullPointerException("All fields must be filled");
        }
        else if(!Pattern.matches(EMAIL, registrationRequest.getEmail())){
            throw new WrongEmailException("Wrong email");
        }

        User user = new User(
                registrationRequest.getLogin(),
                passwordEncoder.encode(registrationRequest.getPassword()),
                registrationRequest.getEmail(),
                UserRole.USER);

        repository.save(user);
        return "User was registered";
    }

}
