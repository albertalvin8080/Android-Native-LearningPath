package org.albert.x10_pagination.datasources

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.PagingSource
import org.albert.x10_pagination.models.Country
import org.albert.x10_pagination.utils.CountriesDb

class PositionalCountryPagingSource : PagingSource<Int, Country>() {
    private val TAG = this::class.simpleName
    private val source = CountriesDb.getCountries()

    fun deleteById(id: Int) {
        CountriesDb.deleteCountryById(id)
        // When we invalidate this PagingSource, the Pager(...) object inside MainActivityViewModel
        // is responsible for creating a new PagingSource and emitting a new PagingData to its Flow object.
        // That's why it receives a pagingSourceFactory.
        invalidate() // Invalidate this PagingSource
    }

    // params.key is initially null (?)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        return try {
            val startPosition = params.key ?: 0
            val loadSize = params.loadSize

            Log.v(TAG, "Loading data from position $startPosition with size $loadSize")
            // drop == equivalent to SQL SKIP (exclusive)
            // take == equivalent to SQL LIMIT
            val list = source.drop(startPosition).take(loadSize)

            val nextKey = if (list.isEmpty()) null else startPosition + loadSize
            val prevKey = if (startPosition == 0) null else startPosition - loadSize

            Log.v(TAG, "Loaded ${list.size} items from position $startPosition")

            LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error loading data", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            Log.d(TAG, "prevKey: ${state.closestPageToPosition(anchorPosition)?.prevKey}")
            Log.d(TAG, "nextKey: ${state.closestPageToPosition(anchorPosition)?.nextKey}")
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }
}