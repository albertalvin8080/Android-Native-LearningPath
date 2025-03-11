package org.albert.x10_pagination.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.albert.x10_pagination.data.ContactDatabase
import org.albert.x10_pagination.models.Contact

/**
 * SEQUENCE:
 * 1. We update the database (insert, delete, etc.)
 * 2. We tell PagingSource to invalidate itself using invalidate() inside it.
 * 3. The Pager which holds the PagingSource uses the factory to create a new PagingSource
 * 4. The Pager also emits a new PagingData containing the paginated items to its Flow<PagingData<T>>
 * 5. We collect the emitted value using flow.collectLatest(pagingData -> ...)
 * 6. We pass the new pagingData to the PagingDataAdapter<> using adapter.submit(pagingData)
 * */
class ContactViewModel(db: ContactDatabase) : ViewModel() {
    val pageSize = 15
    val pageConfig = PagingConfig(
        pageSize = pageSize,
        initialLoadSize = 15,
        prefetchDistance = 5,
        enablePlaceholders = false,
    )

    var contactDao = db.contactDao()

    val contacts: Flow<PagingData<Contact>> = Pager(
        config = pageConfig,
        pagingSourceFactory = { contactDao.getContacts() },
    ).flow.cachedIn(viewModelScope)
}