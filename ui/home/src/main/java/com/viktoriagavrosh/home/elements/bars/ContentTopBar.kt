package com.viktoriagavrosh.home.elements.bars

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * App bar to display title and  conditionally display the back navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentTopBar(
    text: String,
    isFavoriteTalesList: Boolean,
    onTopBarHeartClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    val painterId: Int
    val contentDescriptionId: Int

    if (isFavoriteTalesList) {
        painterId = R.drawable.ic_favorite_true
        contentDescriptionId = R.string.favorite_folk_works
    } else {
        painterId = R.drawable.ic_favorite_false
        contentDescriptionId = R.string.all_folk_works
    }
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_large))
            )
        },
        modifier = modifier,
        actions = {
            Icon(
                painter = painterResource(id = painterId),
                contentDescription = stringResource(id = contentDescriptionId),
                modifier = Modifier
                    .clickable {
                        onTopBarHeartClick()
                    }
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_small),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_small)
                    )
                    .size(dimensionResource(id = R.dimen.top_bar_icon_size))
            )
        },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContentTopBarPreview() {
    FairyTalesTheme {
        ContentTopBar(
            text = "Text",
            isFavoriteTalesList = false,
            onTopBarHeartClick = {},
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun FavoriteContentTopBarPreview() {
    FairyTalesTheme {
        ContentTopBar(
            text = "Text",
            isFavoriteTalesList = true,
            onTopBarHeartClick = {},
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        )
    }
}
