package com.viktoriagavrosh.fairytales.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.data.CompositionType
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType

@Composable
fun DetailScreen(
    currentCompositionType: CompositionType,
    selectedComposition: Composition,
    contentType: FairyTalesContentType,
    modifier: Modifier = Modifier,
    onDetailScreenBackClick: () -> Unit
) {
    BackHandler {
        onDetailScreenBackClick()
    }

    var bigCard by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
        colors = if (contentType == FairyTalesContentType.LIST_AND_DETAILS) {
            CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        } else {
            CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        }
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.Center
        ) {

            if (contentType != FairyTalesContentType.LIST_AND_DETAILS
                && currentCompositionType != CompositionType.Puzzles
            ) {
                Image(
                    painter = painterResource(id = selectedComposition.imageId),
                    contentDescription = stringResource(id = selectedComposition.titleId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner))),
                    contentScale = ContentScale.Crop
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_small)
                    )
                    .fillMaxWidth()

            ) {
                Text(
                    text = stringResource(id = selectedComposition.textId),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(
                            dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
            if (currentCompositionType == CompositionType.Puzzles) {
                Row(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            bigCard = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_answer),
                            contentDescription = stringResource(R.string.answer)
                        )
                    }
                }
                if (bigCard) {
                    Answer(
                        selectedComposition = selectedComposition as Composition.Puzzle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_medium)
                            )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}


@Composable
private fun Answer(
    selectedComposition: Composition.Puzzle,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = selectedComposition.imageId),
            contentDescription = stringResource(id = selectedComposition.titleId),
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        dimensionResource(id = R.dimen.corner)
                    )
                ),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = selectedComposition.answerId),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            currentCompositionType = CompositionType.Puzzles,
            selectedComposition = CatalogFairyTales.puzzles[0],
            contentType = FairyTalesContentType.LIST_ONLY,
            onDetailScreenBackClick = {}
        )
    }
}