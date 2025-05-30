package org.albert.x11_room.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.albert.x11_room.data.AppDatabase
import org.albert.x11_room.migrations.MIGRATION_1_2
import org.albert.x11_room.migrations.MIGRATION_2_3
import org.albert.x11_room.model.NoteItem
import org.albert.x11_room.model.NoteList
import org.albert.x11_room.model.NoteListWithNoteItems


/*
* WARNING: This is just for learning. You should use a Repository pattern instead of
* directly using DAOs here. Remember that ViewModel is like a Service.
* */
class NoteListViewModel(context: Context) : ViewModel() {
    val db = Room.databaseBuilder(
        context, AppDatabase::class.java, "note-list-v2-sqlite"
    ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .allowMainThreadQueries()
        .build()

    val dao = db.noteListDao()
    private val _noteListWithNoteItemsFlow =
        MutableStateFlow<List<NoteListWithNoteItems>>(emptyList())
    val noteListWithNoteItemsFlow: StateFlow<List<NoteListWithNoteItems>> =
        _noteListWithNoteItemsFlow.asStateFlow()

    init {
        // Observe the full list initially
        viewModelScope.launch {
            dao.getFlowForNoteListWithNoteItems()
                .collect { _noteListWithNoteItemsFlow.value = it }
        }
    }

    fun insertNoteList(noteList: NoteList) {
        viewModelScope.launch {
            dao.insertAll(noteList)
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            dao.getFlowLikeForNoteListWithNoteItems("%$query%")
                .collect { noteListWithNoteItems ->
                    _noteListWithNoteItemsFlow.value = noteListWithNoteItems
                }
        }
    }

    fun insertNoteListWithLastIdPlusOne() {
        viewModelScope.launch {
            val lastIdPlusOne = dao.getLastId() + 1
            insertNoteList(NoteList(title = "sacrorum_$lastIdPlusOne", id = lastIdPlusOne))
            val itemLastIdPlusOne = db.noteItemDao().getLastId() + 1
            for (j in itemLastIdPlusOne..(itemLastIdPlusOne + 2))
                insertNoteItem(
                    NoteItem(
                        id = j,
                        value = "$j lorem ipsum dolor amet scir sciato",
                        noteListId = lastIdPlusOne,
                        itemOrder = null
                    )
                )
        }
    }

    fun insertNoteItem(noteItem: NoteItem) {
        viewModelScope.launch {
            db.noteItemDao().insert(noteItem)
        }
    }
}