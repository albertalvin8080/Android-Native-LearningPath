package org.albert.x10_pagination.data

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.albert.x10_pagination.models.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact ORDER BY id ASC LIMIT 1")
    fun getTopContact(): Contact

    @Query("SELECT * FROM Contact")
    fun getAllContacts(): List<Contact>

    @Query("SELECT id FROM Contact ORDER BY id DESC LIMIT 1")
    fun getLastId(): Int

    // Paging library will internally use SKIP and LIMIT sql keywords in order to fetch the data in
    // batches.
    @Query("SELECT * FROM Contact ORDER BY id ASC")
//    fun getContacts(): DataSource.Factory<Int, Contact>
    fun getContacts(): PagingSource<Int, Contact>

    @Insert
    fun insertContact(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}