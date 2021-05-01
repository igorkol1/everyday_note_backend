package com.igorkol.everyday_note_backend.note

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoteService {

    @Autowired
    lateinit var noteInMemoryRepository: NoteInMemoryRepository

    fun getAllNotes(): List<Note> {
        return noteInMemoryRepository.getAllNotes()
    }

    fun saveNote(note: Note): Note {
        return noteInMemoryRepository.save(note)
    }
}
