package com.example.a3m1l.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a3m1l.data.model.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<NoteEntity>>

    @Insert
    fun insertAll(vararg note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

}
