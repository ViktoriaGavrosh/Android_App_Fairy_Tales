package com.viktoriagavrosh.fairytales.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales


@Composable
fun FairyTalesApp() {
    Scaffold(
        topBar = {
            FairyTalesTopAppBar()
        }
    ) {
        ListCompositions(
            compositions = CatalogFairyTales.fairyTales.shuffled(),
            contentPadding = it
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FairyTalesTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.title_fairy_tales),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun FairyTalesAppPreview() {
    FairyTalesApp()
}
