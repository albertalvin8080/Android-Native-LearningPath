package org.albert.x10_pagination.datasources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.albert.x10_pagination.models.Country
import org.albert.x10_pagination.utils.CountriesDb

class PagedCountriesPagingSource : PagingSource<Int, Country>() {
    private val TAG = this::class.simpleName
    private val source = CountriesDb.getCountries()

    // params.key is initially null (?)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        return try {
            val page = params.key ?: 1
            Log.v(TAG, "Loading page $page")
            val pagedCountries = source.filter { country -> country.page == page }
            Log.v(TAG, "Loaded ${pagedCountries.size} items for page $page")

            val nextKey = if (pagedCountries.isEmpty()) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            LoadResult.Page(
                data = pagedCountries, prevKey = prevKey, nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error loading data", e)
            LoadResult.Error(e)
        }
    }

    /*
     * It checks the PagingState, which contains information about the most recently accessed pages.
     * It attempts to find the anchor position, which is the most recently accessed index in the list.
     * It then finds the closest Page to that anchor and determines whether to use its prevKey + 1 or nextKey - 1 as the new key for refreshing.
     */
    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}