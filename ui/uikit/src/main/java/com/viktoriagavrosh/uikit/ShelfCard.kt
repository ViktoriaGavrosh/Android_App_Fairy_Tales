package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Card for display one item
 */
@Composable
internal fun ShelfCard(
    modifier: Modifier = Modifier,
    itemId: Int,
    title: String,
    imageUrl: String?,
    isFavorite: Boolean = false,
    isHeartShow: Boolean = false,
    isBlur: Boolean = false,
    minLineText: Int = 1,
    onCardClick: (Int) -> Unit,
    onHeartClick: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onCardClick(itemId) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
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
            CardText(
                itemId = itemId,
                title = title,
                isFavorite = isFavorite,
                isHeartShow = isHeartShow,
                onHeartClick = onHeartClick,
                modifier = Modifier.fillMaxWidth(),
                minLineText = minLineText
            )
            if (imageUrl != null) {
                TaleImage(
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
private fun CardText(
    itemId: Int,
    title: String,
    isFavorite: Boolean,
    isHeartShow: Boolean,
    onHeartClick: (Int) -> Unit,
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
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.weight(1F),
            minLines = minLineText
        )
        if (isHeartShow) {
            HeartIcon(
                isFavorite = isFavorite,
                modifier = Modifier
                    .clickable { onHeartClick(itemId) }
                    .padding(start = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun HeartIcon(
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

@Composable
private fun TaleImage(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    isBlur: Boolean = false
) {
    AsyncImage(
        model = ImageRequest
            .Builder(context = LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = title,
        error = painterResource(id = R.drawable.error),
        placeholder = painterResource(id = R.drawable.placeholder),
        contentScale = ContentScale.Crop,
        modifier = if (isBlur) {
            modifier
                .aspectRatio(1.5F)
                .blur(
                    radiusX = 20.dp,
                    radiusY = 20.dp,
                    edgeTreatment = BlurredEdgeTreatment(
                        RoundedCornerShape(dimensionResource(id = R.dimen.corner))
                    )
                )
        } else {
            modifier
                .aspectRatio(1.5F)
                .clip(
                    RoundedCornerShape(
                        dimensionResource(id = R.dimen.corner)
                    )
                )
        }
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShortTitleCardPreview() {
    FairyTalesTheme {
        ShelfCard(
            itemId = 1,
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
        ShelfCard(
            itemId = 1,
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
        ShelfCard(
            itemId = 1,
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
        ShelfCard(
            itemId = 1,
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
        ShelfCard(
            itemId = 1,
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
        ShelfCard(
            itemId = 1,
            title = "This is the title of the card",
            imageUrl = null,
            onCardClick = {},
            onHeartClick = {}
        )
    }
}
