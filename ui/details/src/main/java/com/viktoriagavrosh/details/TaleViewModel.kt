package com.viktoriagavrosh.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.details.utils.toDetailScreenState
import com.viktoriagavrosh.repositories.settings.SettingsRepository
import com.viktoriagavrosh.repositories.tale.TaleRepository
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
 * ViewModel to retrieve and update item from the [TaleRepository]'s data source
 */
@HiltViewModel(assistedFactory = TaleViewModel.TaleViewModelFactory::class)
class TaleViewModel @AssistedInject constructor(
    @Assisted private val taleId: Int,
    private val taleRepository: TaleRepository,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    internal val tales: StateFlow<DetailScreenState> = taleRepository.getTaleById(taleId)
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
    internal val textSize: StateFlow<Float>
        get() = _textSize.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            0.0F
        )

    /**
     * Update the value of textSize in the data source
     */
    internal fun updateTextSize(textSize: Float) {
        viewModelScope.launch {
            settingsRepository.updateTextSize(textSize)
        }
    }

    @AssistedFactory
    interface TaleViewModelFactory {
        fun create(taleId: Int): TaleViewModel
    }
}

internal sealed class DetailScreenState(val tale: TaleUiDetail? = null) {
    class None : DetailScreenState()
    class Success(tale: TaleUiDetail) : DetailScreenState(tale)
    class Error : DetailScreenState()
}
