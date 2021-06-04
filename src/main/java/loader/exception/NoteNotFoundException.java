package loader.exception;

public class NoteNotFoundException extends Exception{
    public NoteNotFoundException(Long id){
        super(String.valueOf(id));
    }
}
