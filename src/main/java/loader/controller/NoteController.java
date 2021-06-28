package loader.controller;

import loader.entitie.Note;
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

/*    @DeleteMapping("/{userId}/notebook/note/{id}")
    public void deleteNote(@PathVariable long userId, @PathVariable long id) throws NoteNotFoundException {
        noteService.deleteNote(userId, id);
    }*/

    @PostMapping
    public String addNote(@RequestBody Note note){
        if(note.getUsername() == null){
            note.setUsername(getUsername());
        }
        return noteService.addNote(note);
    }

    // TODO: How to get Notes' id
/*    @PutMapping("/{userId}/notebook/note/{id}")
    public void editNote(@PathVariable long userId, @PathVariable long id, String label, String noteString)
            throws NoteNotFoundException {
        noteService.editNote(userId, id, label, noteString);
    }*/

    private String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails)principal).getUsername();
    }
}