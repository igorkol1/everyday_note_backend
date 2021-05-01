package com.igorkol.everyday_note_backend.note

import org.springframework.stereotype.Component

@Component
class NoteInMemoryRepository {

    val notes = mutableListOf<Note>()

    fun getAllNotes(): List<Note> {
        return notes
    }

    fun save(note: Note): Note {
        notes.add(note)
        return note
    }
}
