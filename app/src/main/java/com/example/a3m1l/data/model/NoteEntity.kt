package com.example.a3m1l.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    val title: String = "",
    val description: String = "",
    val time: String = "",
){
@PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

