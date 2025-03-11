package org.albert.x11_room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import org.albert.x11_room.model.NoteItem
import org.albert.x11_room.model.NoteList

@Database(entities = [NoteList::class, NoteItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteListDao(): NoteListDao
    abstract fun noteItemDao(): NoteItemDao
}