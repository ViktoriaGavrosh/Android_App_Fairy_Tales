package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.image.BookImage
import com.viktoriagavrosh.uikit.utils.FairyTaleColors
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Card for display one item
 */
@Composable
fun ItemCard(
    title: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    isHeartShow: Boolean = false,
    isBlur: Boolean = false,
    minLineText: Int = 1,
    onCardClick: () -> Unit,
    onHeartClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onCardClick() },
        colors = CardDefaults.cardColors(
            containerColor = FairyTaleColors.getRandomColor()
        )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            CardTitle(
                title = title,
                isFavorite = isFavorite,
                isHeartShow = isHeartShow,
                onHeartClick = onHeartClick,
                modifier = Modifier.fillMaxWidth(),
                minLineText = minLineText
            )
            if (imageUrl != null) {
                BookImage(
                    title = title,
                    imageUrl = imageUrl,
                    isBlur = isBlur,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun CardTitle(
    title: String,
    isFavorite: Boolean,
    isHeartShow: Boolean,
    onHeartClick: () -> Unit,
    minLineText: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = FairyTaleColors.textColor,
            modifier = Modifier.weight(1F),
            minLines = minLineText
        )
        if (isHeartShow) {
            HeartIcon(
                isFavorite = isFavorite,
                modifier = Modifier
                    .clickable { onHeartClick() }
                    .padding(start = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShortTitleCardPreview() {
    FairyTalesTheme {
        ItemCard(
            title = "Title",
            imageUrl = "",
            onCardClick = {},
            onHeartClick = {}
        )
    }
}

@Preview
@Composable
private fun WithHeartCardPreview() {
    FairyTalesTheme {
        ItemCard(
            title = "Title",
            imageUrl = "",
            isHeartShow = true,
            isFavorite = false,
            onCardClick = {},
            onHeartClick = {}
        )
    }
}

@Preview
@Composable
private fun MiddleTitleCardPreview() {
    FairyTalesTheme {
        ItemCard(
            title = "This is the title of the card",
            imageUrl = "",
            isHeartShow = true,
            isFavorite = false,
            onCardClick = {},
            onHeartClick = {}
        )
    }
}

@Preview
@Composable
private fun FavoriteCardPreview() {
    FairyTalesTheme {
        ItemCard(
            title = "Title",
            imageUrl = "",
            isFavorite = true,
            isHeartShow = true,
            onCardClick = {},
            onHeartClick = {}
        )
    }
}


@Preview
@Composable
private fun BlurCardPreview() {
    FairyTalesTheme {
        ItemCard(
            title = "Title",
            imageUrl = "",
            isBlur = true,
            onCardClick = {},
            onHeartClick = {}
        )
    }
}

@Preview
@Composable
private fun WithoutImageCardPreview() {
    FairyTalesTheme {
        ItemCard(
            title = "This is the title of the card",
            imageUrl = null,
            onCardClick = {},
            onHeartClick = {}
        )
    }
}
