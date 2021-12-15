package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;
    private int checkSuccess;

    public NotesController(NotesService notesService)
    {
        this.notesService = notesService;
    }

    @GetMapping
    public String getCredentials(@ModelAttribute("newNote") NotesForm notesForm , Model model)
    {
        return "home";
    }

    @PostMapping
    public String postCredentials(Authentication authentication, @ModelAttribute("newNote") NotesForm notesForm, Model model)
    {
        // Check if the record already exists!
        if(notesForm.getNoteId()!= null) { this.checkSuccess = notesService.updateNote(notesForm, authentication.getName()); }
        else { this.checkSuccess = notesService.addNote(notesForm, authentication.getName()); }

        if (checkSuccess >= 1 ) { model.addAttribute("result", "success"); }
        else { model.addAttribute("result", "failure"); }

        return "result";
    }

    @GetMapping("/delete_note")
    public String delete_note(Model model, @RequestParam(name="noteId") Integer noteId)
    {
        this.checkSuccess = notesService.deleteNote(noteId);
        if (checkSuccess >= 1 ) { model.addAttribute("result", "success"); }
        else { model.addAttribute("result", "failure"); }

        model.addAttribute("result", "success");

        return "result";
    }

}
