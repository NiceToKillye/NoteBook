package loader.controller;

import loader.entitie.Note;
import loader.exception.EmptyNoteException;
import loader.exception.NoteNotFoundException;
import loader.service.NoteService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class NoteController {

    private final NoteService noteService;
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("notes", noteService.getAllNotes(getUsername()));
        return "user";
    }

    @DeleteMapping
    public void deleteAllNotes(){
        noteService.deleteAllNotes(getUsername());
    }

    @PostMapping
    public void addNote(@RequestBody Note note) throws EmptyNoteException {
        if(note.getUsername() == null){
            note.setUsername(getUsername());
        }
        noteService.addNote(note);
    }

    private String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails)principal).getUsername();
    }

    /*-------DOESN'T WORK ON SITE, WORKS WITH TALEND API TESTER------------*/

    @DeleteMapping("/user/notebook/note/{id}")
    public void deleteNote(@PathVariable long id) throws NoteNotFoundException {
        noteService.deleteNote(getUsername(), id);
    }

    @PutMapping("/user/notebook/note/{id}")
    public void editNote(@PathVariable long id, String label, String noteString)
            throws NoteNotFoundException {
        noteService.editNote(getUsername(), id, label, noteString);
    }
}