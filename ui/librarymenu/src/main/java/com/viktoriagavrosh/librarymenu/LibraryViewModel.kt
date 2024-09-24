package com.viktoriagavrosh.librarymenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.repositories.MenuRepository
import com.viktoriagavrosh.repositories.ShelfRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve and update item from the [ShelfRepository]'s data source
 */
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(LibraryUiState())

    init {
        initLibraryUiState()
    }

    internal val uiState: StateFlow<LibraryUiState>
        get() = _uiState

    internal fun initLibraryUiState() {
        updateRandomTale()
    }

    internal fun updateLastTale() {
        viewModelScope.launch {
            val newLastTaleId = uiState.first().randomTaleId
            repository.updateLastTaleId(newLastTaleId)
        }
    }

    internal fun updateRandomTale() {
        viewModelScope.launch {
            val requestResultRandomTale = repository.getRandomTaleId().first()
            if (requestResultRandomTale is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true,
                    )
                }
            } else {
                val randomTaleId = requestResultRandomTale.data ?: 1
                _uiState.update {
                    it.copy(
                        randomTaleId = randomTaleId,
                    )
                }
            }
        }
    }
}

internal data class LibraryUiState(
    val randomTaleId: Int = 0,
    val isError: Boolean = false,
)
