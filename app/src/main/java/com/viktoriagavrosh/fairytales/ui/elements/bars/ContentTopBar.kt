package com.viktoriagavrosh.fairytales.ui.elements.bars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R

/**
 * App bar to display title and  conditionally display the back navigation
 */
@Composable
fun ContentTopBar(
    text: String,
    isShowHomeScreen: Boolean,
    isFavoriteWorks: Boolean,
    onDetailScreenBackClick: () -> Unit,
    onTopBarHeartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isShowHomeScreen) {
            DetailScreenTopBar(
                text = text,
                onDetailScreenBackClick = onDetailScreenBackClick
            )
        } else {
            HomeScreenTopBar(
                text = text,
                isFavoriteWorks = isFavoriteWorks,
                onTopBarHeartClick = onTopBarHeartClick
            )
        }
    }
}

@Composable
private fun HomeScreenTopBar(
    text: String,
    isFavoriteWorks: Boolean,
    onTopBarHeartClick: () -> Unit,
    //modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.displayLarge,
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_large),
                top = dimensionResource(id = R.dimen.padding_medium)
            )

    )
    Icon(
        painter = if (isFavoriteWorks) {
            painterResource(id = R.drawable.ic_favorite_true)
        } else {
            painterResource(id = R.drawable.ic_favorite_false)
        },
        contentDescription = if (isFavoriteWorks) {
            stringResource(R.string.favorite_folk_works)
        } else {
            stringResource(R.string.all_folk_works)
        },
        modifier = Modifier
            .clickable {
                onTopBarHeartClick()
            }
            .padding(
                end = dimensionResource(id = R.dimen.padding_medium),
                bottom = dimensionResource(id = R.dimen.padding_small)
            )
            .size(dimensionResource(id = R.dimen.top_bar_icon_size))
    )
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
fun ContentTopBarHomeScreenPreview() {
    ContentTopBar(
        text = "Text",
        isShowHomeScreen = true,
        onDetailScreenBackClick = {},
        isFavoriteWorks = false,
        onTopBarHeartClick = {}
    )
}

@Preview
@Composable
fun ContentTopBarDetailScreenPreview() {
    ContentTopBar(
        text = "Text",
        isShowHomeScreen = false,
        onDetailScreenBackClick = {},
        isFavoriteWorks = false,
        onTopBarHeartClick = {}
    )
}