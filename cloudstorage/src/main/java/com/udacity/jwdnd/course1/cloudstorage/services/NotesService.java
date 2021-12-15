package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotesService {

    private final NotesMapper notesMapper;
    private final UserService userService;

    public NotesService(NotesMapper notesMapper, UserService userService) {
        this.notesMapper = notesMapper;
        this.userService = userService;
    }

    public int addNote(NotesForm notesForm, String userName) {
        return notesMapper.insert(new Notes(null, notesForm.getTitle(),
                notesForm.getDescription(), userService.getUser(userName).getUserId()));
    }

    public List<Notes> getNotes(String userName) {
        return this.notesMapper.getNotes(userService.getUser(userName).getUserId());
    }

    public int deleteNote(Integer noteId)
    {
        return this.notesMapper.delete(noteId);
    }
    public int checkExist(Integer noteId) { return this.notesMapper.checkExist(noteId);}

    public int updateNote(NotesForm notesForm, String userName) {
        return notesMapper.update(new Notes(notesForm.getNoteId(), notesForm.getTitle(),
                notesForm.getDescription(), userService.getUser(userName).getUserId()));

    }

}


