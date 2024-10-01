package com.viktoriagavrosh.infomenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.infomenu.model.TaleInfo
import com.viktoriagavrosh.infomenu.utils.toTaleInfo
import com.viktoriagavrosh.repositories.MenuRepository
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
 * ViewModel to retrieve and update item from the [MenuRepository]'s data source
 */
@HiltViewModel(assistedFactory = InfoViewModel.InfoViewModelFactory::class)
class InfoViewModel @AssistedInject constructor(
    @Assisted private val taleId: Int,
    private val repository: MenuRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InfoUiState())
    internal val uiState: StateFlow<InfoUiState>
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
                updateLastTale(tale.id)
            }
        }
    }

    private fun updateLastTale(taleId: Int) {
        viewModelScope.launch {
            repository.updateLastTaleId(taleId)
        }
    }

    @AssistedFactory
    interface InfoViewModelFactory {
        fun create(taleId: Int): InfoViewModel
    }
}

internal data class InfoUiState(
    val tale: TaleInfo = TaleInfo(),
    val isError: Boolean = false,
)
