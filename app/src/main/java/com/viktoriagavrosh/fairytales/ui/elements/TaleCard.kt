package com.viktoriagavrosh.fairytales.ui.elements

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
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

/**
 * Card for display tale
 */
@Composable
fun TaleCard(
    tale: TaleUi,
    onCardClick: (TaleUi) -> Unit,
    onHeartClick: (TaleUi) -> Unit,
    modifier: Modifier = Modifier,
    minLineText: Int = 1,    // TODO check it !
) {
    Card(
        onClick = { onCardClick(tale) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium,
                    )
                )
        ) {
            CardText(
                tale = tale,
                onHeartClick = onHeartClick,
                modifier = Modifier.fillMaxWidth(),
                minLineText = minLineText,
            )
            TaleImage(
                title = tale.title,
                imageUrl = tale.imageUrl ?: "",   // TODO something with it
                isBlur = tale.genre == Genre.Puzzle,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CardText(
    tale: TaleUi,
    onHeartClick: (TaleUi) -> Unit,
    minLineText: Int,
    modifier: Modifier = Modifier,
) {
    val heartIconId: Int
    val contentDescriptionHeartIconId: Int

    if (tale.isFavorite) {
        heartIconId = R.drawable.ic_favorite_true
        contentDescriptionHeartIconId = R.string.favorite
    } else {
        heartIconId = R.drawable.ic_favorite_false
        contentDescriptionHeartIconId = R.string.not_favorite
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = tale.title,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.weight(1F),
            minLines = minLineText,
        )
        Icon(
            painter = painterResource(id = heartIconId),
            contentDescription = stringResource(id = contentDescriptionHeartIconId),
            modifier = Modifier
                .clickable { onHeartClick(tale) }
                .padding(start = dimensionResource(id = R.dimen.padding_small)),
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShortTitleCardPreview() {
    FairyTalesTheme {
        TaleCard(
            tale = TaleUi(title = "Story"),
            onCardClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MiddleTitleCardPreview() {
    FairyTalesTheme {
        TaleCard(
            tale = TaleUi(title = "This is the name of the story"),
            onCardClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LongTitleCardPreview() {
    FairyTalesTheme {
        TaleCard(
            tale = TaleUi(
                title = "This is the very long name of the very long story " +
                        "about something interesting"
            ),
            onCardClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoriteCardPreview() {
    FairyTalesTheme {
        TaleCard(
            tale = TaleUi(
                title = "Story",
                isFavorite = true,
            ),
            onCardClick = {},
            onHeartClick = {},
        )
    }
}
