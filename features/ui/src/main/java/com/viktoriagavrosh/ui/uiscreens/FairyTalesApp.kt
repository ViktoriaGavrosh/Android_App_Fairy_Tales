package com.viktoriagavrosh.ui.uiscreens

import androidx.annotation.DrawableRes
import com.viktoriagavrosh.ui.R

/**
 * Top level composable that represents screens for the application
 */
/*
@Composable
fun FairyTalesApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
    viewModel: FairyTalesViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val logic = UILogic(
        onTabClick = viewModel::updateCompositionType,
        onCardClick = viewModel::navigateToDetailScreen,
        onDetailScreenBackClick = viewModel::navigateToHomeScreen,
        onHeartClick = viewModel::updateWorkFavorite,
        onTopBarHeartClick = viewModel::updateListFavoriteWorks
    )

    if (uiState.isShowHomeScreen) {
        ContentScreen(
            uiState = uiState,
            logic = logic,
            windowSize = windowSize,
            modifier = modifier
        )
    } else {
        DetailScreen(
            folkWork = uiState.selectedWork,
            logic = logic,
            isPuzzleType = uiState.folkWorkType == FolkWorkType.Puzzle,
            isStoryType = uiState.folkWorkType == FolkWorkType.Story,
            isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded,
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun FairyTalesAppPreview() {
    FairyTalesApp(
        windowSize = WindowWidthSizeClass.Compact
    )
}


 */

/**
 * Enum class to describe the destination of tab navigation
 */
enum class FolkWorkType(
    @DrawableRes val iconId: Int,
    val textId: Int
) {
    Story(
        iconId = R.drawable.ic_text,
        textId = R.string.title_fairy_tales
    ),
    Puzzle(
        iconId = R.drawable.ic_puzzle,
        textId = R.string.title_puzzle
    ),
    Poem(
        iconId = R.drawable.ic_poem,
        textId = R.string.title_poem
    ),
    Game(
        iconId = R.drawable.ic_game,
        textId = R.string.title_game
    ),
    Lullaby(
        iconId = R.drawable.ic_lullaby,
        textId = R.string.lullaby
    );
}



