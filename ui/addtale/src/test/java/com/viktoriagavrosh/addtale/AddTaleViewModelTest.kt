package com.viktoriagavrosh.addtale

import com.viktoriagavrosh.addtale.fake.FakeAddRepository
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.addtale.utils.TaleGenre
import com.viktoriagavrosh.addtale.utils.toTale
import com.viktoriagavrosh.repositories.model.Tale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AddTaleViewModelTest {
    @get:Rule
    internal val testDispatcher = TestDispatcherRule()

    @Test
    fun addTaleViewModel_addTale_taleAdded() {
        runTest {
            val repository = FakeAddRepository()
            val viewModel = AddTaleViewModel(repository)
            val expectedTale = NewTale().toTale()
            viewModel.addTale()
            val actualTale = repository.getTaleById(0).first().data ?: Tale(id = 5)
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun addTaleViewModel_updateTitle_uiStateUpdated() {
        runTest {
            val newTitle = "New title"
            val viewModel = AddTaleViewModel(FakeAddRepository())
            val expectedTale = NewTale().copy(title = newTitle)
            viewModel.updateTitle(newTitle)
            val actualTale = viewModel.uiState.first().newTale
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun addTaleViewModel_updateText_uiStateUpdated() {
        runTest {
            val newText = "New text"
            val viewModel = AddTaleViewModel(FakeAddRepository())
            val expectedTale = NewTale().copy(text = newText)
            viewModel.updateText(newText)
            val actualTale = viewModel.uiState.first().newTale
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun addTaleViewModel_updateGenre_uiStateUpdated() {
        runTest {
            val newGenre = TaleGenre.Fairy
            val viewModel = AddTaleViewModel(FakeAddRepository())
            val expectedTale = NewTale().copy(taleGenre = newGenre)
            viewModel.updateGenre(newGenre.title)
            val actualTale = viewModel.uiState.first().newTale
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun addTaleViewModel_updateIsNight_uiStateUpdated() {
        runTest {
            val newValue = true
            val viewModel = AddTaleViewModel(FakeAddRepository())
            val expectedTale = NewTale().copy(isNight = newValue)
            viewModel.updateIsNight(newValue)
            val actualTale = viewModel.uiState.first().newTale
            assertEquals(expectedTale, actualTale)
        }
    }

    @Test
    fun addTaleViewModel_isTaleValid_uiStateUpdated() {
        runTest {
            val newTitle = "Title"
            val newText = "Text"
            val viewModel = AddTaleViewModel(FakeAddRepository())
            viewModel.updateTitle(newTitle)
            viewModel.updateText(newText)
            val actualValue = viewModel.uiState.first().isTaleValid
            assert(actualValue)
        }
    }

    @Test
    fun addTaleViewModel_isTaleNotValidWithEmptyTitle_uiStateUpdated() {
        runTest {
            val newTitle = ""
            val viewModel = AddTaleViewModel(FakeAddRepository())
            viewModel.updateTitle(newTitle)
            val actualValue = viewModel.uiState.first().isTaleValid
            assert(!actualValue)
        }
    }

    @Test
    fun addTaleViewModel_isTaleNotValidWithEmptyText_uiStateUpdated() {
        runTest {
            val newText = ""
            val viewModel = AddTaleViewModel(FakeAddRepository())
            viewModel.updateText(newText)
            val actualValue = viewModel.uiState.first().isTaleValid
            assert(!actualValue)
        }
    }
}
