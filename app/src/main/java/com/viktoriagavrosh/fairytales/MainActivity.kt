package com.viktoriagavrosh.fairytales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.ui.ListFairyTales
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FairyTalesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FairyTalesApp()
                }
            }
        }
    }
}

@Composable
fun FairyTalesApp() {
    Scaffold(
        topBar = {
            FairyTalesTopAppBar()
        }
    ) {
        ListFairyTales(
            fairyTales = CatalogFairyTales.fairyTales.shuffled(),
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FairyTalesTheme {
        FairyTalesApp()
    }
}