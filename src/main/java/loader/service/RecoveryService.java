package loader.service;
import loader.entitie.User;
import loader.entitie.UserRepository;
import loader.exception.WrongRecoveryEmailException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class RecoveryService {

    private final String EMAIL = "\\w+@\\w+\\.[a-z]+";
    private final JavaMailSender mailSender;
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public RecoveryService(JavaMailSender mailSender, UserRepository repository, PasswordEncoder encoder) {
        this.mailSender = mailSender;
        this.repository = repository;
        this.encoder = encoder;
    }

    public void sendPassword(String email) throws WrongRecoveryEmailException {
        Optional<User> userList = repository.findUserByEmail(email);

        if(!Pattern.matches(EMAIL, email)){
            throw new WrongRecoveryEmailException("Email is wrong!");
        }
        else if(userList.isEmpty()){
            throw new UsernameNotFoundException("User with this email was not found!");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        String password = RandomStringUtils.randomAlphabetic(32);

        message.setTo(email);
        message.setFrom("javanotesender@gmail.com");
        message.setSubject("Change password");
        message.setText("Your new password: " + password);

        User user = userList.get();
        user.setPassword(encoder.encode(password));
        repository.save(user);

        mailSender.send(message);
    }
}
