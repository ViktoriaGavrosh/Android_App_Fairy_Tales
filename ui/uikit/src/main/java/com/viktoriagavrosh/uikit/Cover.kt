package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uikit.decor.FlowersRow
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display cover
 *
 * @param text text on cover
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun Cover(
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.testTag(stringResource(R.string.cover_test_tag)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FlowersRow(modifier = Modifier.testTag(stringResource(R.string.top_flowersrow_test_tag)))
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
        FlowersRow(modifier = Modifier.testTag(stringResource(R.string.bottom_flowersrow_test_tag)))
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CoverPreview() {
    FairyTalesTheme {
        Cover(
            text = "Text"
        )
    }
}
