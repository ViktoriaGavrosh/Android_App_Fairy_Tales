package com.viktoriagavrosh.infomenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.infomenu.utils.toTaleInfo
import com.viktoriagavrosh.repositories.MenuRepository
import com.viktoriagavrosh.repositories.ReadRepository
import com.viktoriagavrosh.repositories.utils.RequestResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from the [ReadRepository]'s data source
 */
@HiltViewModel(assistedFactory = InfoViewModel.InfoViewModelFactory::class)
class InfoViewModel @AssistedInject constructor(
    @Assisted private val taleId: Int,
    private val repository: MenuRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReaderUiState())
    internal val uiState: StateFlow<ReaderUiState>
        get() = _uiState

    init {
        updateUiState()
    }

    internal fun updateUiState() {
        viewModelScope.launch {
            val requestResultTale = repository.getTaleById(taleId).first()

            if (requestResultTale is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            } else {
                val tale = requestResultTale.data?.toTaleInfo() ?: TaleInfo()

                _uiState.update {
                    it.copy(
                        tale = tale
                    )
                }
            }
        }
    }

    /**
     * Update the value of an item field is_favorite in the data source
     */
    internal fun updateTaleFavorite() {
        viewModelScope.launch {
            val tale = uiState.first().tale
            repository.updateFavoriteTale(tale.id, !tale.isFavorite)
        }

        updateUiState()          // TODO check : uiState might update without it ???
    }

    internal fun updateLastTale() {
        viewModelScope.launch {
            val tale = uiState.first().tale
            repository.updateLastTaleId(tale.id)
        }
    }

    @AssistedFactory
    interface InfoViewModelFactory {
        fun create(taleId: Int): InfoViewModel
    }
}

internal data class ReaderUiState(
    val tale: TaleInfo = TaleInfo(),
    val isError: Boolean = false,
)
