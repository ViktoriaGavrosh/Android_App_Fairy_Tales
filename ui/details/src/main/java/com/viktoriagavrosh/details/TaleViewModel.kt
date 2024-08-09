package com.viktoriagavrosh.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.repositories.RequestResult
import com.viktoriagavrosh.repositories.SettingsRepository
import com.viktoriagavrosh.repositories.TaleRepository
import com.viktoriagavrosh.repositories.model.Tale
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

    private var _textSize: Flow<Float> = settingsRepository.getTextSize()
    internal val textSize: StateFlow<Float>
        get() = _textSize.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            0.0F
        )

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

internal fun RequestResult<Tale>.toDetailScreenState(): DetailScreenState {
    return when (this) {
        is RequestResult.Success -> DetailScreenState.Success(
            tale = data.toTaleUiDetail()
        )

        is RequestResult.Error -> DetailScreenState.Error()
    }
}

fun Tale.toTaleUiDetail(): TaleUiDetail {
    return TaleUiDetail(
        id = id,
        genre = genre,
        title = title,
        text = text,
        answer = answer,
        imageUri = imageUri,
        audioUri = audioUri,
        isFavorite = isFavorite
    )
}
