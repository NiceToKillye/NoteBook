package loader.exception;

public class NoteNotFoundException extends Exception{
    public NoteNotFoundException(long id, String username){
        super("Note with id " + id + " for user " + username + " was not found");
    }
}
