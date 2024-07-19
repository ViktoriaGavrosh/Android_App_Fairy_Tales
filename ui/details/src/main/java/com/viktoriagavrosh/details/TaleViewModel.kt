package com.viktoriagavrosh.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.details.model.FolkWorkUiDetails
import com.viktoriagavrosh.fairytales.model.Tale
import com.viktoriagavrosh.repositories.FolkWorkRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve and update item from the [FolkWorkRepository]'s data source
 */
@HiltViewModel(assistedFactory = TaleViewModel.TaleViewModelFactory::class)
class TaleViewModel @AssistedInject constructor(
    @Assisted private val taleId: Int,
    private val folkWorkRepository: FolkWorkRepository,
) : ViewModel() {

    val folkWorkUiDetails: StateFlow<FolkWorkUiDetails> = folkWorkRepository.getWorkById(taleId)
        .map { tale ->
            tale.toFolkWorkUiDetails()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            FolkWorkUiDetails()
        )

    @AssistedFactory
    interface TaleViewModelFactory {
        fun create(taleId: Int): TaleViewModel
    }
}

fun Tale.toFolkWorkUiDetails(): FolkWorkUiDetails {
    return FolkWorkUiDetails(
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
