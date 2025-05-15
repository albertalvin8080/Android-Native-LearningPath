package org.albert.x18_topsearchbar.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.albert.x18_topsearchbar.enums.SearchWidgetState

class MainViewModel: ViewModel() {
    private val _searchWidgetState = mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = this._searchWidgetState

    private val _searchTextState = mutableStateOf("")
    val searchTextState: State<String> = this._searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        this._searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        this._searchTextState.value = newValue
    }
}