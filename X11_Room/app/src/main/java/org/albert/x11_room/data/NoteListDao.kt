package org.albert.x11_room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.albert.x11_room.model.NoteList
import org.albert.x11_room.model.NoteListWithNoteItems

@Dao
interface NoteListDao {
    @Query("SELECT * FROM NoteList ORDER BY id ASC")
    fun getAll(): List<NoteList>

    @Transaction
    @Query("SELECT * FROM NoteList") // JOIN behind the scenes.
    fun getNoteListWithNoteItems(): List<NoteListWithNoteItems>

    @Transaction
    @Query("SELECT * FROM NoteList") // JOIN behind the scenes.
    fun getFlowForNoteListWithNoteItems(): Flow<List<NoteListWithNoteItems>>

    @Query("SELECT * FROM NoteList n WHERE n.title LIKE :query ORDER BY n.id ASC")
    fun getFlowLikeForNoteListWithNoteItems(query: String): Flow<List<NoteListWithNoteItems>>

    @Insert
    fun insertAll(vararg noteList: NoteList)

    @Delete
    fun delete(noteList: NoteList)

    @Query("SELECT id FROM NoteList ORDER BY id DESC LIMIT 1")
    fun getLastId(): Int
}