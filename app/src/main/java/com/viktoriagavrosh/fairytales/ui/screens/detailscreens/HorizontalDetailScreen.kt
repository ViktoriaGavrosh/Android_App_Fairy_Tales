package com.viktoriagavrosh.fairytales.ui.screens.detailscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.elements.FolkWorkImage
import com.viktoriagavrosh.fairytales.ui.utils.MockData

/**
 * Composable to display details of selected [FolkWork] on expanded screen
 */
@Composable
fun HorizontalDetailScreen(
    folkWork: FolkWork,
    isPuzzleType: Boolean,
    isStoryType: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isPuzzleType) {
                ImageHorizontal(
                    title = folkWork.title,
                    imageUri = folkWork.imageUri ?: ""
                )
            }
            Row {
                Spacer(
                    modifier = if (isStoryType) {
                        Modifier.width(dimensionResource(id = R.dimen.padding_small))
                    } else {
                        Modifier.weight(1F)
                    }
                )
                TextDetail(
                    text = folkWork.text,
                    modifier = Modifier
                        .weight(3F)
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
                Spacer(
                    modifier = if (isStoryType) {
                        Modifier.width(dimensionResource(id = R.dimen.padding_small))
                    } else {
                        Modifier.weight(1F)
                    }
                )
            }
            if (isPuzzleType) {
                AnswerHorizontal(
                    answer = folkWork.answer ?: "",
                    imageUri = folkWork.imageUri ?: "",
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
private fun ImageHorizontal(
    title: String,
    imageUri: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(1F))
        FolkWorkImage(
            title = title,
            imageUri = imageUri,
            modifier = Modifier
                .weight(2F)
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
private fun AnswerHorizontal(
    answer: String,
    imageUri: String,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    if (bigCard) {
        Row {
            Spacer(modifier = Modifier.weight(1F))
            Answer(
                answer = answer,
                imageUri = imageUri,
                isBigImage = false,
                modifier = modifier
                    .weight(2F)
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.weight(1F))
        }
    } else {
        Button(
            onClick = { bigCard = true },
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.answer_button),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun HorizontalDetailScreenPreview() {
    HorizontalDetailScreen(
        folkWork = MockData.fakeFolkWork,
        isPuzzleType = true,
        isStoryType = false
    )
}