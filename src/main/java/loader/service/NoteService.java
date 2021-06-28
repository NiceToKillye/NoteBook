package loader.service;

import loader.entitie.Note;
import loader.entitie.NoteRepository;
import loader.exception.NoteNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public Iterable<Note> getAllNotes(String username){
        return noteRepository.findAllByUsername(username);
    }

    public void deleteAllNotes(String username){
        noteRepository.deleteAllByUsername(username);
    }

/*    public void deleteNote(long userId, long id) throws NoteNotFoundException{
        if(!noteRepository.existsByIdAndUserId(id, userId)){
            throw new NoteNotFoundException(userId, id);
        }
        else {
            noteRepository.deleteById(id);
        }
    }*/

    public String addNote(Note note){
        return noteRepository.save(note).toString();
    }

/*    public void editNote(long userId, long id, String label, String noteString) throws NoteNotFoundException {
        Note note = noteRepository.findNoteByIdAndUserId(id, userId)
                .orElseThrow(() -> new NoteNotFoundException(userId, id));

        if(label != null && !label.equals(note.getLabel())){
            note.setLabel(label);
        }
        if(noteString != null && !noteString.equals(note.getNote())){
            note.setNote(noteString);
        }
        noteRepository.save(note);
    }*/
}