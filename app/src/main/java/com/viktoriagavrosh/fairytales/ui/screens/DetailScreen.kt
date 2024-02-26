package com.viktoriagavrosh.fairytales.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.viktoriagavrosh.fairytales.data.FolkWorkType
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType

@Composable
fun DetailScreen(
    currentFolkWorkType: FolkWorkType,
    selectedWork: FolkWork,
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
                && currentFolkWorkType != FolkWorkType.Puzzle
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bee),     // TODO my placeholder
                    contentDescription = null,
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
                    text = selectedWork.text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(
                            dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
            if (currentFolkWorkType == FolkWorkType.Puzzle) {
                /*Row(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                }

                 */
                if (bigCard) {
                    Answer(
                        selectedWork = selectedWork,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_medium)
                            )
                    )
                } else {
                    Button(
                        onClick = {
                            bigCard = true
                        },
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.padding_extra_large))
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.answer_button),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}


@Composable
private fun Answer(
    selectedWork: FolkWork,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.bee),     // TODO my placeholder
            contentDescription = null,
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
            text = selectedWork.answer ?: "",               // TODO my обработать по-другому
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
            currentFolkWorkType = FolkWorkType.Puzzle,
            selectedWork = FolkWork(
                id = 0,
                genre = "story",
                title = "Story",
                text = "Story",
                answer = null,
                imageUri = null,
                audioUri = null,
                isFavorite = false
            ),
            contentType = FairyTalesContentType.LIST_ONLY,
            onDetailScreenBackClick = {}
        )
    }
}