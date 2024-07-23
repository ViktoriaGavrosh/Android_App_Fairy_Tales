package com.viktoriagavrosh.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.viktoriagavrosh.details.model.TaleUiDetail
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display details of selected [TaleUiDetail]
 */
@Composable
fun DetailScreen(
    taleId: Int,
    isExpandedScreen: Boolean,
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: TaleViewModel = hiltViewModel(
        creationCallback = { factory: TaleViewModel.TaleViewModelFactory ->
            factory.create(taleId)
        }
    )

    val tale by viewModel.tales.collectAsState()

    Column(
        modifier = modifier
    ) {
        DetailsTopBar(
            text = tale.title,
            onDetailScreenBackClick = onDetailScreenBackClick,
            modifier = Modifier.fillMaxWidth()
        )
        ContentDetailScreen(
            tale = tale,
            isExpandedScreen = isExpandedScreen,
            modifier = if (isExpandedScreen) {
                Modifier
                    .fillMaxSize()
                    .padding(end = dimensionResource(id = R.dimen.right_padding_horizontal_screen))
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .verticalScroll(rememberScrollState())
            } else {
                Modifier
                    .fillMaxHeight()
                    .padding(dimensionResource(id = R.dimen.padding_extra_large))
                    .verticalScroll(rememberScrollState())
            }
        )
    }
}

/**
 * App bar to display title and  conditionally display the back navigation
 */
@Composable
private fun DetailsTopBar(
    text: String,
    onDetailScreenBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(
            onClick = { onDetailScreenBackClick() },
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_medium)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
                modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size))
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_small)
                )
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsTopBarPreview() {
    FairyTalesTheme {
        DetailsTopBar(
            text = "Top bar",
            onDetailScreenBackClick = {}
        )
    }
}
