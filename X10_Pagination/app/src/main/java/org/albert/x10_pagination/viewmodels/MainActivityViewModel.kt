package org.albert.x10_pagination.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.albert.x10_pagination.datasources.PositionalCountryPagingSource
import org.albert.x10_pagination.models.Country

/**
 * SEQUENCE:
 * 1. We update the database (insert, delete, etc.)
 * 2. We tell PagingSource to invalidate itself using invalidate() inside it.
 * 3. The Pager which holds the PagingSource uses the factory to create a new PagingSource
 * 4. The Pager also emits a new PagingData containing the paginated items to its Flow<PagingData<T>>
 * 5. We collect the emitted value using flow.collectLatest(pagingData -> ...)
 * 6. We pass the new pagingData to the PagingDataAdapter<> using adapter.submit(pagingData)
 * */
class MainActivityViewModel : ViewModel() {
    val pageSize = 15

    var currentPagingSource: PositionalCountryPagingSource? = null

    fun deleteById(id: Int) {
        currentPagingSource?.deleteById(id)
    }

    private val pagingConfig = PagingConfig(
        pageSize = pageSize,
        initialLoadSize = 15,
        prefetchDistance = 5,
        enablePlaceholders = false // false -> don't allow null entities
    )

    val countries: Flow<PagingData<Country>> = Pager(
        config = pagingConfig,
//      Equivalent to => pagingSourceFactory = { -> PositionalCountryPagingSource() },
        pagingSourceFactory = {
            PositionalCountryPagingSource().also { currentPagingSource = it }
        },
    ).flow
        // cachedIn(viewModelScope) ensures that Flow is retained within the ViewModel's
        // lifecycle, preventing redundant fetches when the UI is recreated.
        .cachedIn(viewModelScope)
}