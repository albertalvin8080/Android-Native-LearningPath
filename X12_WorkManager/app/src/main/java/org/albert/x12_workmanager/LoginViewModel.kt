package org.albert.x12_workmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginFlow = MutableStateFlow(false)
    val loginFlow = _loginFlow.asStateFlow()

    fun login(username: String, password: String) {
        // Creates a coroutine associated with the viewModelScope.
        viewModelScope.launch {
            if (password == "123") {
                _loginFlow.value = true
            } else {
                _loginFlow.value = false
            }
        }
    }
}