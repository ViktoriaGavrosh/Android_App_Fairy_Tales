package com.viktoriagavrosh.home.elements

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
import com.viktoriagavrosh.home.R
import com.viktoriagavrosh.home.model.TaleUiHome
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Card for display [TaleUiHome]
 */
@Composable
internal fun TaleCard(
    modifier: Modifier = Modifier,
    tale: TaleUiHome,
    minLineText: Int = 1,
    onCardClick: (TaleUiHome) -> Unit,
    onHeartClick: (TaleUiHome) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onCardClick(tale) },
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
                tale = tale,
                onHeartClick = onHeartClick,
                modifier = Modifier.fillMaxWidth(),
                minLineText = minLineText
            )
            TaleImage(
                title = tale.title,
                imageUri = tale.imageUri ?: "",
                isBlur = tale.genre == Genre.Puzzle,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CardText(
    tale: TaleUiHome,
    onHeartClick: (TaleUiHome) -> Unit,
    minLineText: Int,
    modifier: Modifier = Modifier
) {
    val painterId: Int
    val contentDescriptionId: Int

    if (tale.isFavorite) {
        painterId = R.drawable.ic_favorite_true
        contentDescriptionId = R.string.favorite
    } else {
        painterId = R.drawable.ic_favorite_false
        contentDescriptionId = R.string.not_favorite
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tale.title,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.weight(1F),
            minLines = minLineText
        )
        Icon(
            painter = painterResource(id = painterId),
            contentDescription = stringResource(id = contentDescriptionId),
            modifier = Modifier
                .clickable { onHeartClick(tale) }
                .padding(start = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
private fun TaleImage(
    modifier: Modifier = Modifier,
    title: String,
    imageUri: String,
    isBlur: Boolean = false
) {
    AsyncImage(
        model = ImageRequest
            .Builder(context = LocalContext.current)
            .data(imageUri)
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
        TaleCard(
            tale = TaleUiHome(
                title = "Story"
            ),
            onCardClick = {},
            onHeartClick = {}
        )
    }
}

@Preview
@Composable
private fun MiddleTitleCardPreview() {
    FairyTalesTheme {
        TaleCard(
            tale = TaleUiHome(
                title = "This is the name of the story"
            ),
            onCardClick = {},
            onHeartClick = {}
        )
    }
}

@Preview
@Composable
private fun FavoriteCardPreview() {
    FairyTalesTheme {
        TaleCard(
            tale = TaleUiHome(
                title = "Story",
                isFavorite = true,
            ),
            onCardClick = {},
            onHeartClick = {}
        )
    }
}
