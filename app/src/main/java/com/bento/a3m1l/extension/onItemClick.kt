package com.bento.a3m1l.extension

import com.bento.a3m1l.data.model.NoteEntity

interface onItemClick {
    fun onClick(note: NoteEntity)
    fun onLongClick(note: NoteEntity)
}