package org.albert.x10_pagination.datasources

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.PagingSource
import org.albert.x10_pagination.models.Country
import org.albert.x10_pagination.utils.CountriesDb

class KeyedCountriesPagingSource : PagingSource<Int, Country>() {
    private val TAG = this::class.simpleName
    private val source = CountriesDb.getCountries()

    // params.key is initially null (?)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        return try {
            val key = params.key ?: 1
            Log.v(TAG, "Loading page with key $key")

            val startIndex = source.indexOfFirst { it.id == key }
            if (startIndex == -1) return LoadResult.Page(emptyList(), null, null)

            val list = source.drop(startIndex).take(params.loadSize)
            val nextKey = list.lastOrNull()?.id?.plus(1)
            val prevKey = if (startIndex == 0) null else list.firstOrNull()?.id?.minus(1)

            Log.v(TAG, "Loaded ${list.size} items from key $key")

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
            state.closestItemToPosition(anchorPosition)?.id
        }
    }
}
