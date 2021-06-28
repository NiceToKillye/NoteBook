package loader.exception;

public class NoteNotFoundException extends Exception{
    public NoteNotFoundException(long id, long userId){
        super("Note with id " + id + " for user #" + userId + " was not found");
    }
}
