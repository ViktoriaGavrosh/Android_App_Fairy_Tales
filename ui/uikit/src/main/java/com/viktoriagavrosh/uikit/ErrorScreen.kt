package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun ErrorScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.testTag(stringResource(R.string.error_screen_test_tag)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_extra_large))
        ) {
            Text(
                text = stringResource(R.string.error_button_text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,

                )
        }
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalErrorScreenPreview() {
    FairyTalesTheme {
        ErrorScreen(
            onButtonClick = {},
        )
    }
}
