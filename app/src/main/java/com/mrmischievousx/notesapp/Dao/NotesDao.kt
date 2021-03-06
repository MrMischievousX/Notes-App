package com.mrmischievousx.notesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mrmischievousx.notesapp.Model.Notes

@Dao
interface NotesDao {

    @Query("Select * from Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("Delete from Notes where id=:id")
    fun deleteNotes(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNotes(notes: Notes)
}