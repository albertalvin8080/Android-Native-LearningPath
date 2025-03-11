package org.albert.x11_room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.albert.x11_room.model.NoteItem

@Dao
interface NoteItemDao {
    @Query("SELECT * FROM NoteItem ORDER BY id ASC")
    fun getAll(): List<NoteItem>

    @Insert
    fun insert(noteItem: NoteItem)

    @Delete
    fun delete(noteItem: NoteItem)

    @Query("SELECT id FROM NoteItem ORDER BY id DESC LIMIT 1")
    fun getLastId(): Int
}