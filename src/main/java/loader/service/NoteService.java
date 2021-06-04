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

    public Iterable<Note> getAll(){ return noteRepository.findAll(); }
    public void deleteAll(){ noteRepository.deleteAll(); }

    public Note getNote(Long id) throws NoteNotFoundException{
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }

    public String addNote(Note note){
        return noteRepository.save(note).toString();
    }

    public void deleteNote(Long id) throws NoteNotFoundException{
        if(!noteRepository.existsById(id)){
            throw new NoteNotFoundException(id);
        }
        else {
            noteRepository.deleteById(id);
        }
    }

    public void editNote(Long id, String label, String noteString) throws NoteNotFoundException {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));

        if(label != null && !label.equals(note.getLabel())){
            note.setLabel(label);
        }
        if(noteString != null && !noteString.equals(note.getNote())){
            note.setNote(noteString);
        }
        noteRepository.save(note);
    }
}