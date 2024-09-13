package com.viktoriagavrosh.home.viewmodel

/*
class TalesListViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun talesListViewModel_init_verifyUiState_TaleType() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            val actualType = viewModel.uiState.value.genre
            assertEquals(Genre.Story, actualType)
        }
    }

    @Test
    fun homeViewModel_init_verifyUiState_IsFavoriteTaleList() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            val actualValue = viewModel.uiState.value.isFavoriteTalesShown
            assertEquals(false, actualValue)
        }
    }

    @Test
    fun homeViewModel_init_verifyScreenState() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            val actual = viewModel.screenState.first() is HomeScreenState.Success
            assert(actual)
        }
    }

    @Test
    fun homeViewModel_updateGenre_updateUiStateTaleType() {
        runTest {
            val expectedGenre = Genre.Puzzle
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())
            viewModel.updateGenre(expectedGenre)
            val actualGenre = viewModel.uiState.value.genre
            assertEquals(expectedGenre, actualGenre)
        }
    }

    @Test
    fun homeViewModel_updateGenre_updateScreenStateTales() {
        runTest {
            val newGenre = Genre.Puzzle
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())
            val expectedTales = FakeSource().fakeListTales
                .filter { it.genre == Genre.Puzzle.genreName }
                .map { it.toTaleUiHome() }
            viewModel.updateGenre(newGenre)
            val actualTales = viewModel.screenState.first().tales ?: emptyList()
            assertEquals(expectedTales, actualTales)
        }
    }

    @Test
    fun homeViewModel_updateFavoriteTalesList_updateUiStateIsFavoriteTalesList() {
        runTest {
            val viewModel = TalesListViewModel(taleRepository = FakeTaleRepository())

            viewModel.updateFavoriteTalesList()
            val actualValue = viewModel.uiState.value.isFavoriteTalesShown
            assertEquals(true, actualValue)
        }
    }

}


 */
