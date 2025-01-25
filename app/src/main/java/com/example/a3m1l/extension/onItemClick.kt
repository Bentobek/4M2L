package com.example.a3m1l.extension

import com.example.a3m1l.data.model.NoteEntity

interface onItemClick {
    fun onClick(note: NoteEntity)
    fun onLongClick(note: NoteEntity)
}