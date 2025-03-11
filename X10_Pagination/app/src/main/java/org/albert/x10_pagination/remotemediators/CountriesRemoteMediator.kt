package org.albert.x10_pagination.remotemediators

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import org.albert.x10_pagination.models.Country

import androidx.paging.PagingState
import androidx.paging.LoadType

// BROKEN
//class CountriesRemoteMediator(): RemoteMediator<Int, Country>() {
//    @OptIn(ExperimentalPagingApi::class)
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, Country>
//    ): MediatorResult {
//        return try {
//            val page = when (loadType) {
//                LoadType.REFRESH -> 1 // for refresh, load first page
//                LoadType.PREPEND -> {
//                    // No need to load more items from the beginning, handle as needed
//                    return MediatorResult.Success(endOfPaginationReached = true)
//                }
//                LoadType.APPEND -> {
//                    // Get the last item or calculate page number for next load
//                    state.lastItemOrNull()?.id ?: 1
//                }
//                else -> MediatorResult.Error(e)
//            }
//        } catch (e: Exception) {
//            Log.e("CountriesRemoteMediator", "Error loading data", e)
//            MediatorResult.Error(e)
//        }
//    }
//}


// WARNING: BoundaryCallback<> will be replaced with RemoteMediator<Key, Entity>
//class CountriesBoundaryCallback : PagedList.BoundaryCallback<Country>() {
//    override fun onZeroItemsLoaded() {
//        Log.d(this::class.simpleName, "onZeroItemsLoaded")
//    }
//
//    override fun onItemAtEndLoaded(itemAtEnd: Country) {
//        Log.d(this::class.simpleName, "onItemAtEndLoaded")
//    }
//
//    override fun onItemAtFrontLoaded(itemAtFront: Country) {
//        Log.d(this::class.simpleName, "onItemAtFrontLoaded")
//    }
//}