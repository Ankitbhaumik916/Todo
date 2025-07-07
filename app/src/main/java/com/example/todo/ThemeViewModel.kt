package com.example.todo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ThemeViewModel(private val context: Context) : ViewModel() {

    // Backing field for dark theme state
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme: StateFlow<Boolean> = _darkTheme.asStateFlow()

    init {
        // Observe the saved theme preference
        SettingsDataStore.isDarkModeEnabled(context)
            .onEach { isDark ->
                _darkTheme.value = isDark
            }
            .launchIn(viewModelScope)
    }

    // Toggle and save theme preference
    fun toggleTheme(enabled: Boolean) {
        viewModelScope.launch {
            SettingsDataStore.setDarkMode(context, enabled)
        }
    }
}
