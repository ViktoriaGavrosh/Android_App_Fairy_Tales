package com.viktoriagavrosh.fairytales.ui.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.fairytales.data.repositories.SettingsRepository
import com.viktoriagavrosh.fairytales.data.repositories.TaleRepository
import com.viktoriagavrosh.fairytales.data.repositories.utils.toDetailScreenState
import com.viktoriagavrosh.fairytales.model.TaleUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update items from [TaleRepository]`s for [DetailScreen]
 */
@HiltViewModel(assistedFactory = DetailViewModel.TaleViewModelFactory::class)
class DetailViewModel @AssistedInject constructor(
    @Assisted private val taleId: Int,
    taleRepository: TaleRepository,  // TODO change to one repository
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    val tales: StateFlow<DetailScreenState> = taleRepository.getTaleById(taleId)
        .map { it.toDetailScreenState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            DetailScreenState.None()
        )

    /**
     * TextSize from data source
     */
    private var _textSize: Flow<Float> = settingsRepository.getTextSize()
    val textSize: StateFlow<Float>
        get() = _textSize.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            0.0F
        )

    /**
     * Update value of textSize in data source
     */
    fun updateTextSize(textSize: Float) {
        viewModelScope.launch {
            settingsRepository.updateTextSize(textSize)
        }
    }

    @AssistedFactory
    interface TaleViewModelFactory {
        fun create(taleId: Int): DetailViewModel
    }
}

sealed class DetailScreenState(val tale: TaleUi? = null) {
    class None : DetailScreenState()
    class Success(tale: TaleUi) : DetailScreenState(tale)
    class Error : DetailScreenState()
}
