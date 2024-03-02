package com.viktoriagavrosh.fairytales.ui.screens

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
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType

@Composable
fun ListCompositionsScreen(
    folkWorks: List<FolkWork>,
    currentFolkWorkType: FolkWorkType,
    selectedWork: FolkWork,
    navigationType: FairyTalesNavigationType,
    onCardClick: (FolkWork) -> Unit,
    onHeartClicked: (FolkWork, FolkWorkType) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(folkWorks) { folkWork ->
            CardComposition(
                currentFolkWorkType = currentFolkWorkType,
                selectedWork = selectedWork,
                navigationType = navigationType,
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
    currentFolkWorkType: FolkWorkType,
    selectedWork: FolkWork,
    navigationType: FairyTalesNavigationType,
    folkWork: FolkWork,
    onCardClick: (FolkWork) -> Unit,
    onHeartClicked: (FolkWork, FolkWorkType) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable {
                onCardClick(selectedWork)
            },
        colors = if (
            folkWork == selectedWork
            && navigationType == FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER
        ) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                                onHeartClicked(folkWork, currentFolkWorkType)
                            }
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite_false),
                        contentDescription = stringResource(R.string.not_favorite),
                        modifier = Modifier
                            .clickable {
                            onHeartClicked(folkWork, currentFolkWorkType)
                        }
                    )
                }
            }

            AsyncImage(
                model = ImageRequest
                    .Builder(context = LocalContext.current)
                    .data(folkWork.imageUri)
                    .crossfade(true)
                    .build(),
                contentDescription = folkWork.title,
                error = painterResource(id = R.drawable.error),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = if (currentFolkWorkType == FolkWorkType.Puzzle) {
                    Modifier
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
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5F)
                        .clip(
                            RoundedCornerShape(
                                dimensionResource(id = R.dimen.corner)
                            )
                        )
                }
            )
            /*
                        if (currentFolkWorkType == FolkWorkType.Puzzle
                            || navigationType == FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER      // TODO my delete after fix horizontal screen
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.bee),     // TODO my placeholder
                                contentDescription = null,
                                modifier = if (currentFolkWorkType == FolkWorkType.Puzzle) {
                                    Modifier
                                        .fillMaxWidth()
                                        .blur(
                                            radiusX = 20.dp,
                                            radiusY = 20.dp,
                                            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(16.dp))
                                        )
                                        .clickable { onCardClick(folkWork) }
                                } else {
                                    Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            RoundedCornerShape(
                                                dimensionResource(id = R.dimen.corner)
                                            )
                                        )
                                        .clickable { onCardClick(folkWork) }
                                },
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.bee),     // TODO my placeholder
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(
                                            dimensionResource(id = R.dimen.corner)
                                        )
                                    )
                                    .clickable {
                                        bigCard = !bigCard

                                    },
                                contentScale = ContentScale.Crop
                            )
                            if (bigCard) {
                                TextFairyTale(
                                    folkWork = folkWork,
                                    onCardClick = onCardClick
                                )
                            }
                        } */
        }
    }
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
        currentFolkWorkType = FolkWorkType.Story,
        selectedWork = fakeFolkWork,
        navigationType = FairyTalesNavigationType.BOTTOM_NAVIGATION,
        folkWork = fakeFolkWork,
        onCardClick = {},
        onHeartClicked = {_,_ ->}
    )
}


@Preview
@Composable
fun ListCompositionsPreview() {
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
    ListCompositionsScreen(
        currentFolkWorkType = FolkWorkType.Puzzle,
        selectedWork = fakeFolkWork,
        folkWorks = emptyList(),
        navigationType = FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER,
        onCardClick = {},
        onHeartClicked = {_, _ ->  }
    )
}