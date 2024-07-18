package com.viktoriagavrosh.details.uiscreens.screens.detailscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.viktoriagavrosh.details.R
import com.viktoriagavrosh.details.model.FolkWorkUiDetails
import com.viktoriagavrosh.details.uiscreens.TaleViewModel
import com.viktoriagavrosh.fairytales.ui.screens.detailscreens.HorizontalDetailScreen
import com.viktoriagavrosh.fairytales.ui.screens.detailscreens.VerticalDetailScreen

/**
 * Composable to display details of selected [FolkWorkUiDetails]
 */
@Composable
fun DetailScreen(
    folkWorkId: Int,
    //logic: UILogic,   TODO delete
    //isPuzzleType: Boolean,
    //isStoryType: Boolean,
    modifier: Modifier = Modifier,
    onDetailScreenBackClick: () -> Unit,
    isExpandedScreen: Boolean,
) {
    val viewModel: TaleViewModel = hiltViewModel(
        creationCallback = { factory: TaleViewModel.TaleViewModelFactory ->
            factory.create(folkWorkId)
        }
    )

    val folkWork by viewModel.folkWorkUiDetails.collectAsState()

    Column(
        modifier = modifier
    ) {
        DetailsTopBar(
            text = folkWork.title,
            //isShowHomeScreen = false,  TODO delete
            onDetailScreenBackClick = onDetailScreenBackClick,
            //isFavoriteWorks = false,
            //onTopBarHeartClick = {}
        )
        if (isExpandedScreen) {
            HorizontalDetailScreen(
                folkWork = folkWork,
                isPuzzleType = folkWork.genre == "puzzle",  // TODO change
                isStoryType = folkWork.genre == "story",    // TODO change
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = dimensionResource(id = R.dimen.right_padding_horizontal_screen))
                    .testTag(stringResource(R.string.horizontal_detail_screen))
            )
        } else {
            VerticalDetailScreen(
                folkWork = folkWork,
                isPuzzleType = folkWork.genre == "puzzle",  // TODO change
                modifier = Modifier
                    .fillMaxHeight()
                    .testTag(stringResource(R.string.vertical_detail_screen))
            )
        }
    }
}

/**
 * App bar to display title and  conditionally display the back navigation
 */
@Composable
private fun DetailsTopBar(
    text: String,
    //isShowHomeScreen: Boolean,
    // isFavoriteWorks: Boolean,  TODO delete
    onDetailScreenBackClick: () -> Unit,
    //onTopBarHeartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        DetailScreenTopBar(
            text = text,
            onDetailScreenBackClick = onDetailScreenBackClick
        )
    }
}

@Composable
private fun DetailScreenTopBar(
    text: String,
    onDetailScreenBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
    ) {
        IconButton(
            onClick = { onDetailScreenBackClick() },
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_medium)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
                modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size))
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

/*
@Preview
@Composable
fun DetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            isPuzzleType = true,
            isStoryType = false,
            folkWork = MockData.fakeFolkWork,
            logic = UILogic(),
            isExpandedScreen = false
        )
    }
}

@Preview(widthDp = 1000)
@Composable
fun DetailHorizontalScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            isPuzzleType = false,
            isStoryType = true,
            folkWork = MockData.fakeFolkWork,
            logic = UILogic(),
            isExpandedScreen = true
        )
    }
}


 */
