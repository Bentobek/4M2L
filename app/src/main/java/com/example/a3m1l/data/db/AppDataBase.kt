package com.example.a3m1l.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a3m1l.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 2 , exportSchema = true)
abstract class AppDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao



}