package loader.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String login){
        super(login);
    }
}
