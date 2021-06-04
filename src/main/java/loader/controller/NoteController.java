package loader.controller;

import loader.entitie.Note;
import loader.exception.NoteNotFoundException;
import loader.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    private final NoteService noteService;
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("notes", noteService.getAll());
        return "index";
    }

    @GetMapping("/notes")
    public Iterable<Note> list(){
        return noteService.getAll();
    }

    @DeleteMapping("/notes")
    public void deleteAll(){
        noteService.deleteAll();
    }

    @GetMapping("/notes/{id}")
    public Note get(@PathVariable Long id) throws NoteNotFoundException {
        return noteService.getNote(id);
    }

    @PostMapping(value = "/notes")
    @ResponseBody
    public String add(@RequestBody Note note){
        return noteService.addNote(note);
    }

    @DeleteMapping("/notes/{id}")
    public void delete(@PathVariable Long id) throws NoteNotFoundException {
        noteService.deleteNote(id);
    }

    @PutMapping("/notes/{id}")
    public void edit(@PathVariable Long id, String label, String noteString) throws NoteNotFoundException {
        noteService.editNote(id, label, noteString);
    }
}
