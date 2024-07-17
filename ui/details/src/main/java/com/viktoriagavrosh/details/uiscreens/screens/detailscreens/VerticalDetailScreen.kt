package com.viktoriagavrosh.fairytales.ui.screens.detailscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.viktoriagavrosh.details.R
import com.viktoriagavrosh.details.model.FolkWorkUiDetails

/**
 * Composable to display details of selected [FolkWorkUiDetails] on compact and medium screens
 */
@Composable
fun VerticalDetailScreen(
    folkWork: FolkWorkUiDetails,
    isPuzzleType: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Spacer(modifier = Modifier.weight(1F))
        DetailContent(
            folkWork = folkWork,
            isPuzzleType = isPuzzleType,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
private fun DetailContent(
    folkWork: FolkWorkUiDetails,
    isPuzzleType: Boolean,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isPuzzleType) {
            FolkWorkImage(
                title = folkWork.title,
                imageUri = folkWork.imageUri ?: "",
                isBlur = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
        TextDetail(
            text = folkWork.text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
        )
        if (isPuzzleType) {
            if (bigCard) {
                Answer(
                    answer = folkWork.answer ?: "",
                    imageUri = folkWork.imageUri ?: "",
                    isBigImage = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_medium))
                )
            } else {
                Button(
                    onClick = { bigCard = true },
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
}

/**
 * Composable to display title [FolkWorkUiDetails]
 */
@Composable
internal fun TextDetail(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
    }
}

/**
 * Composable to display answer of puzzle
 */
@Composable
internal fun Answer(
    answer: String,
    imageUri: String,
    isBigImage: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        FolkWorkImage(
            title = answer,
            imageUri = imageUri,
            modifier = if (isBigImage) {
                Modifier.fillMaxWidth()
            } else {
                Modifier
            }
        )
        Text(
            text = answer,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
