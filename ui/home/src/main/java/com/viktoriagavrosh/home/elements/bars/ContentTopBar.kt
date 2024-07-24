package com.viktoriagavrosh.home.elements.bars

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
@Composable
internal fun ContentTopBar(
    text: String,
    isFavoriteTalesList: Boolean,
    onTopBarHeartClick: () -> Unit,
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

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
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
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContentTopBarPreview() {
    FairyTalesTheme {
        ContentTopBar(
            text = "Text",
            isFavoriteTalesList = false,
            onTopBarHeartClick = {}
        )
    }
}

@Preview
@Composable
private fun FavoriteContentTopBarPreview() {
    FairyTalesTheme {
        ContentTopBar(
            text = "Text",
            isFavoriteTalesList = true,
            onTopBarHeartClick = {}
        )
    }
}
