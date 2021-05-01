package com.igorkol.everyday_note_backend.note

import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note,Long>
