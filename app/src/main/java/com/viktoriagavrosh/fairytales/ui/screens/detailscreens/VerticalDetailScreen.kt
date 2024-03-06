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
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.elements.FolkWorkImage

@Composable
fun VerticalDetailScreen(
    folkWork: FolkWork,
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
    folkWork: FolkWork,
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
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
        TextDetail(
            text = folkWork.text,
            modifier = Modifier.fillMaxWidth()
        )
        if (isPuzzleType) {
            if (bigCard) {
                Answer(
                    selectedWork = folkWork,
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

@Composable
private fun TextDetail(
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
