package com.igorkol.everyday_note_backend.note

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/note")
class NoteController {

    @Autowired
    lateinit var noteService: NoteService

    @GetMapping("/list")
    fun getNotes(): List<Note> {
        return noteService.getAllNotes()
    }

    @PostMapping("/save")
    fun saveNote(@RequestBody note: Note): Note {
        return noteService.saveNote(note)
    }

}
