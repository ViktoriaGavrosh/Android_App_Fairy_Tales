package com.viktoriagavrosh.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.home.elements.bars.BottomNavigateBar
import com.viktoriagavrosh.home.elements.bars.ExpandedScreenVerticalBar
import com.viktoriagavrosh.home.elements.bars.VerticalNavigationRail

/**
 * Composable to display different screens depending on window size
 */
@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass,
    onCardClick: (TaleUiHome) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TalesListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (windowSize) {
        WindowWidthSizeClass.Expanded -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    ExpandedScreenVerticalBar(
                        selectedType = uiState.taleType,
                        onTabClick = viewModel::updateTaleType,
                        modifier = Modifier.width(
                            dimensionResource(id = R.dimen.permanent_drawer_sheet_width)
                        )
                    )
                }
            ) {
                ContentScreen(
                    tales = uiState.tales,
                    topBarTextId = uiState.taleType.textId,
                    isFavoriteTalesList = uiState.isFavoriteTalesList,
                    onHeartClick = viewModel::updateTaleFavorite,
                    onTopBarHeartClick = viewModel::updateFavoriteTalesList,
                    onCardClick = onCardClick,
                    isExpandedScreen = true
                )
            }
        }

        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                ContentScreen(
                    tales = uiState.tales,
                    topBarTextId = uiState.taleType.textId,
                    isFavoriteTalesList = uiState.isFavoriteTalesList,
                    onHeartClick = viewModel::updateTaleFavorite,
                    onTopBarHeartClick = viewModel::updateFavoriteTalesList,
                    isExpandedScreen = false,
                    onCardClick = onCardClick,
                    modifier = Modifier.weight(1F)
                )
                BottomNavigateBar(
                    selectedType = uiState.taleType,
                    onTabClick = viewModel::updateTaleType,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        else -> {
            Row(
                modifier = modifier
            ) {
                VerticalNavigationRail(
                    selectedType = uiState.taleType,
                    onTabClick = viewModel::updateTaleType,
                    modifier = Modifier.fillMaxHeight()
                )
                ContentScreen(
                    tales = uiState.tales,
                    topBarTextId = uiState.taleType.textId,
                    isFavoriteTalesList = uiState.isFavoriteTalesList,
                    onHeartClick = viewModel::updateTaleFavorite,
                    onTopBarHeartClick = viewModel::updateFavoriteTalesList,
                    onCardClick = onCardClick,
                    isExpandedScreen = false
                )
            }
        }
    }
}
