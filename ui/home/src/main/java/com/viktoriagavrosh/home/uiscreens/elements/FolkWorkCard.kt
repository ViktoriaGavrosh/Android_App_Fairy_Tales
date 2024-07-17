package com.viktoriagavrosh.home.uiscreens.elements

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
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.utils.MockData
import com.viktoriagavrosh.home.R

/**
 * Card for display [FolkWork]
 */
@Composable
internal fun FolkWorkCard(
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

@Composable
private fun FolkWorkImage(
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

