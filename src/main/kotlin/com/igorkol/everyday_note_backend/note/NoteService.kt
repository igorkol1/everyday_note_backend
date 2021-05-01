package com.igorkol.everyday_note_backend.note

import com.igorkol.everyday_note_backend.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NoteService {

    @Autowired
    lateinit var noteRepository: NoteRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun getAllNotes(): List<Note> {
        return noteRepository.findAll()
    }

    fun saveNote(note: NoteDto): Note {
        val user = userRepository.findById(note.userId)

        if (user.isPresent) {
            val newNote = Note(
                id = 0,
                text = note.text,
                updateDate = LocalDateTime.now(),
                user = user.get()
            )
            return noteRepository.save(newNote)
        } else {
            throw Exception("User not found")
        }
    }

}
