package loader.service;

import loader.entitie.Note;
import loader.entitie.NoteRepository;
import loader.exception.EmptyNoteException;
import loader.exception.NoteNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public Iterable<Note> getAllNotes(String username){
        return noteRepository.findAllByUsername(username);
    }

    @Transactional
    public void deleteAllNotes(String username){
        noteRepository.deleteAllByUsername(username);
    }

    public void addNote(Note note) throws EmptyNoteException {
        if(note.getLabel().isEmpty() || note.getNote().isEmpty()){
            throw new EmptyNoteException("All fields must be filled");
        }
        noteRepository.save(note);
    }

    /*USED BY DELETE NOTE, EDIT NOTE*/

    public void deleteNote(String username, long id) throws NoteNotFoundException {
        if(!noteRepository.existsByIdAndUsername(id, username)){
            throw new NoteNotFoundException(id, username);
        }
        else {
            noteRepository.deleteById(id);
        }
    }

    public void editNote(String username, long id, String label, String noteString) throws NoteNotFoundException {
        Note note = noteRepository.findNoteByIdAndUsername(id, username)
                .orElseThrow(() -> new NoteNotFoundException(id, username));

        if(label != null && !label.equals(note.getLabel())){
            note.setLabel(label);
        }
        if(noteString != null && !noteString.equals(note.getNote())){
            note.setNote(noteString);
        }
        noteRepository.save(note);
    }
}