package org.albert.x10_pagination.data

import androidx.room.Database
import androidx.room.RoomDatabase
import org.albert.x10_pagination.models.Contact

@Database(entities = arrayOf(Contact::class), version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}