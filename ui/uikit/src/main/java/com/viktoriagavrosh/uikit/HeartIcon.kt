package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display dark of bright heart icon
 *
 * @param isFavorite if true, dark heart icon is shown
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun HeartIcon(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isFavorite) {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite_true),
            contentDescription = stringResource(id = R.string.favorite),
            modifier = modifier
        )
    } else {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite_false),
            contentDescription = stringResource(id = R.string.not_favorite),
            modifier = modifier
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoriteHeartIconPreview() {
    FairyTalesTheme {
        HeartIcon(
            isFavorite = true,
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NotFavoriteHeartIconPreview() {
    FairyTalesTheme {
        HeartIcon(
            isFavorite = false,
        )
    }
}
