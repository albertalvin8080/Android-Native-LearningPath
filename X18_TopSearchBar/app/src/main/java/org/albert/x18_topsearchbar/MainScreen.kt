package org.albert.x18_topsearchbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.albert.x18_topsearchbar.appbar.DefaultSearchAppBar
import org.albert.x18_topsearchbar.appbar.SearchAppBar
import org.albert.x18_topsearchbar.enums.SearchWidgetState
import org.albert.x18_topsearchbar.viewmodel.MainViewModel

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MainScreen(mainViewModel: MainViewModel) {
    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        MainAppBar(
            searchTextState,
            searchWidgetState,
            onTextChange = { mainViewModel.updateSearchTextState(it) },
            onSearchClicked = { },
            onCloseClicked = {
                mainViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
            },
            onSearchTriggered = {
                mainViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
            }
        )
    }) { innerPadding ->
    }
}


@Composable
fun MainAppBar(
    searchTextState: String,
    searchWidgetState: SearchWidgetState,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultSearchAppBar(searchTextState, onSearchTriggered)
        }

        SearchWidgetState.OPENED -> {
            SearchAppBar(
                searchTextState,
                onTextChange,
                onCloseClicked,
                onSearchClicked
            )
        }
    }
}