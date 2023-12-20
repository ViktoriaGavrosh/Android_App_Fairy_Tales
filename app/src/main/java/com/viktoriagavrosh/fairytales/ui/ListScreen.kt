package com.viktoriagavrosh.fairytales.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.data.CompositionType
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType
import com.viktoriagavrosh.fairytales.ui.utils.MAX_NUMBER_LINES_IN_TITLE_FAIRY_TALE

@Composable
fun ListCompositionsScreen(
    currentCompositionType: CompositionType,
    selectedComposition: Composition,
    navigationType: FairyTalesNavigationType,
    onCardClick: (Composition) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(currentCompositionType.listItems) { composition ->
            CardComposition(
                currentCompositionType = currentCompositionType,
                selectedComposition = selectedComposition,
                navigationType = navigationType,
                composition = composition,
                onCardClick = onCardClick,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
fun CardComposition(
    currentCompositionType: CompositionType,
    selectedComposition: Composition,
    navigationType: FairyTalesNavigationType,
    composition: Composition,
    onCardClick: (Composition) -> Unit,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier,
        colors = if (
            composition == selectedComposition
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
            Text(
                text = stringResource(id = composition.titleId),
                style = MaterialTheme.typography.displaySmall,
                maxLines = if (currentCompositionType == CompositionType.FairyTales) {
                    MAX_NUMBER_LINES_IN_TITLE_FAIRY_TALE
                } else 1,
                overflow = TextOverflow.Ellipsis
            )
            if (currentCompositionType == CompositionType.Puzzles
                || navigationType == FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER
            ) {
                Image(
                    painter = painterResource(id = composition.imageId),
                    contentDescription = stringResource(id = composition.titleId),
                    modifier = if (currentCompositionType == CompositionType.Puzzles) {
                        Modifier
                            .fillMaxWidth()
                            .blur(
                                radiusX = 20.dp,
                                radiusY = 20.dp,
                                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(16.dp))
                            )
                            .clickable { onCardClick(composition) }
                    } else {
                        Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    dimensionResource(id = R.dimen.corner)
                                )
                            )
                            .clickable { onCardClick(composition) }
                    },
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = composition.imageId),
                    contentDescription = stringResource(id = composition.titleId),
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
                        composition = composition,
                        onCardClick = onCardClick
                    )
                }
            }
        }
    }
}

@Composable
fun TextFairyTale(
    composition: Composition,
    onCardClick: (Composition) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = composition.textId),
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_medium)
            ),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        ReadButton(
            composition = composition,
            onCardClick = onCardClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ReadButton(
    composition: Composition,
    onCardClick: (Composition) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = { onCardClick(composition) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = stringResource(id = R.string.read)
            )
        }
    }
}

@Preview
@Composable
fun ListCompositionsPreview() {
    ListCompositionsScreen(
        currentCompositionType = CompositionType.Puzzles,
        selectedComposition = CatalogFairyTales.puzzles[0],
        navigationType = FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER,
        onCardClick = {}
    )
}