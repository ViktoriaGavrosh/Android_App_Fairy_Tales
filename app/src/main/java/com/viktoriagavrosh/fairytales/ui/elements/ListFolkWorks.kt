package com.viktoriagavrosh.fairytales.ui.elements

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork

@Composable
fun ListFolkWorks(
    folkWorks: List<FolkWork>,
    isBlurImage: Boolean,
    selectedWork: FolkWork,
    onCardClick: (FolkWork) -> Unit,
    onHeartClicked: (FolkWork) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(folkWorks) { folkWork ->
            CardComposition(
                isBlurImage = isBlurImage,
                isSelected = folkWork == selectedWork,
                folkWork = folkWork,
                onCardClick = onCardClick,
                onHeartClicked = onHeartClicked,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
fun CardComposition(
    folkWork: FolkWork,
    isBlurImage: Boolean,
    isSelected: Boolean,
    onCardClick: (FolkWork) -> Unit,
    onHeartClicked: (FolkWork) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable {
                onCardClick(folkWork)
            },
        colors = if (isSelected) {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        } else {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
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
                onHeartClicked = onHeartClicked,
                modifier = Modifier.fillMaxWidth()
            )
            FolkWorkImage(
                title = folkWork.title,
                imageUri = folkWork.imageUri ?: "",
                isBlur = isBlurImage
            )
        }
    }
}

@Composable
private fun CardText(
    folkWork: FolkWork,
    onHeartClicked: (FolkWork) -> Unit,
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
            modifier = Modifier.weight(1F)
        )
        if (folkWork.isFavorite) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_true),
                contentDescription = stringResource(R.string.favorite),
                modifier = Modifier
                    .clickable {
                        onHeartClicked(folkWork)
                    }
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_false),
                contentDescription = stringResource(R.string.not_favorite),
                modifier = Modifier
                    .clickable {
                        onHeartClicked(folkWork)
                    }
            )
        }
    }
}

@Composable
fun FolkWorkImage(
    title: String,
    imageUri: String,
    isBlur: Boolean,
    modifier: Modifier = Modifier
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
                .fillMaxWidth()
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
                .fillMaxWidth()
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
    val fakeFolkWork = FolkWork(
        id = 0,
        genre = "story",
        title = "Story cat cat cat cat cat",
        text = "Story",
        answer = null,
        imageUri = null,
        audioUri = null,
        isFavorite = false
    )
    CardComposition(
        isBlurImage = false,
        isSelected = false,
        folkWork = fakeFolkWork,
        onCardClick = {},
        onHeartClicked = {}
    )
}


@Preview
@Composable
fun ListFolkWorksPreview() {
    val fakeFolkWork = FolkWork(
        id = 0,
        genre = "story",
        title = "Story",
        text = "Story",
        answer = null,
        imageUri = null,
        audioUri = null,
        isFavorite = false
    )
    ListFolkWorks(
        isBlurImage = true,
        selectedWork = fakeFolkWork,
        folkWorks = emptyList(),
        onCardClick = {},
        onHeartClicked = {}
    )
}