package com.viktoriagavrosh.fairytales.ui.elements

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
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.utils.MockData

@Composable
fun FolkWorkCard(
    modifier: Modifier = Modifier,
    folkWork: FolkWork,
    isBlurImage: Boolean,
    minLineText: Int = 1,
    onCardClick: (FolkWork) -> Unit,
    onHeartClick: (FolkWork) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onCardClick(folkWork)
            },
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
                folkWork = folkWork,
                onHeartClick = onHeartClick,
                modifier = Modifier.fillMaxWidth(),
                minLineText = minLineText
            )
            FolkWorkImage(
                title = folkWork.title,
                imageUri = folkWork.imageUri ?: "",
                isBlur = isBlurImage,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CardText(
    folkWork: FolkWork,
    onHeartClick: (FolkWork) -> Unit,
    minLineText: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = folkWork.title,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.weight(1F),
            minLines = minLineText
        )
        if (folkWork.isFavorite) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_true),
                contentDescription = stringResource(R.string.favorite),
                modifier = Modifier.clickable { onHeartClick(folkWork) }
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_false),
                contentDescription = stringResource(R.string.not_favorite),
                modifier = Modifier.clickable { onHeartClick(folkWork) }
            )
        }
    }
}

@Preview
@Composable
fun CardCompositionPreview() {
    FolkWorkCard(
        isBlurImage = false,
        folkWork = MockData.fakeFolkWork,
        onCardClick = {},
        onHeartClick = {}
    )
}

