package com.viktoriagavrosh.details

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.viktoriagavrosh.details.model.FolkWorkUiDetails
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Composable to display details of selected [FolkWorkUiDetails]
 */
@Composable
fun DetailScreen(
    folkWorkId: Int,
    isExpandedScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier,
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
            onDetailScreenBackClick = onDetailScreenBackClick,
            modifier = Modifier.fillMaxWidth()
        )
        if (isExpandedScreen) {
            HorizontalDetailScreen(
                folkWork = folkWork,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = dimensionResource(id = R.dimen.right_padding_horizontal_screen))
                    .testTag(stringResource(R.string.horizontal_detail_screen))
            )
        } else {
            VerticalDetailScreen(
                folkWork = folkWork,
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
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
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

@Preview
@Composable
private fun DetailsTopBarPreview() {
    FairyTalesTheme {
        DetailsTopBar(
            text = "Top bar",
            onDetailScreenBackClick = {}
        )
    }
}
