package com.bento.a3m1l.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bento.a3m1l.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 3 )
abstract class AppDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao



}