package com.viktoriagavrosh.fairytales.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork
import com.viktoriagavrosh.fairytales.ui.FairyTalesUiState
import com.viktoriagavrosh.fairytales.ui.elements.FolkWorkImage
import com.viktoriagavrosh.fairytales.ui.elements.OnlyScreenTopBar
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.UILogic

@Composable
fun DetailScreen(
    uiState: FairyTalesUiState,
    logic: UILogic,
    isPuzzleType: Boolean,
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean
) {
    BackHandler {
        logic.onDetailScreenBackClick()
    }

    var bigCard by remember {
        mutableStateOf(false)
    }

    Column {
        OnlyScreenTopBar(
            text = uiState.selectedWork.title,
            isShowHomeScreen = uiState.isShowHomeScreen,
            currentFolkWorkType = uiState.folkWorkType,
            onDetailScreenBackClick = logic.onDetailScreenBackClick,
            isFavoriteWorks = uiState.isFavoriteWorks,
            onTopBarHeartClicked = logic.onTopBarHeartClicked
        )
        Card(
            modifier = modifier
                .fillMaxHeight()
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .verticalScroll(rememberScrollState()),
            colors = CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                verticalArrangement = Arrangement.Center
            ) {

                if (!isPuzzleType) {
                    FolkWorkImage(
                        title = uiState.selectedWork.title,
                        imageUri = uiState.selectedWork.imageUri ?: "",
                        isBlur = false,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
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
                        text = uiState.selectedWork.text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(
                                dimensionResource(id = R.dimen.padding_small)
                            )
                    )
                }
                if (isPuzzleType) {
                    if (bigCard) {
                        Answer(
                            selectedWork = uiState.selectedWork,
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
}


@Composable
private fun Answer(
    selectedWork: FolkWork,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        FolkWorkImage(
            title = selectedWork.answer ?: "",
            imageUri = selectedWork.imageUri ?: "",
            isBlur = false
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
            isPuzzleType = true,
            uiState = FairyTalesUiState(),
            logic = UILogic({}, {}, {}, {_,_ ->}, {}),
            isExpandedScreen = false
        )
    }
}