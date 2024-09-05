package com.viktoriagavrosh.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display Settings
 */
@Composable
internal fun SettingsContent(
    textSize: Float,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TextSizeRow(
            textSize = textSize,
            onTextSizeUpdate = onTextSizeUpdate,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Composable to display all about textSize
 */
@Composable
private fun TextSizeRow(
    textSize: Float,
    onTextSizeUpdate: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    var sliderPosition by remember {
        mutableFloatStateOf(0.0F)
    }

    // TODO think about how to fix it
    if (sliderPosition == 0.0F) sliderPosition = textSize

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.textsize_title),
            modifier = Modifier.padding(
                end = dimensionResource(id = R.dimen.padding_extra_large),
                start = dimensionResource(id = R.dimen.padding_small)
            ),
            style = MaterialTheme.typography.displaySmall
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(250.dp)
        ) {
            Box(
                modifier = Modifier.height(120.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.letters),
                    fontSize = sliderPosition.sp,
                )
            }
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_extra_large)
                    )
                    .testTag(stringResource(R.string.textsize_slider)),
                valueRange = 8F..100F,
                onValueChangeFinished = { onTextSizeUpdate(sliderPosition) }
            )
        }
    }
}

@Preview
@Composable
private fun TextSizeRowPreview() {
    FairyTalesTheme {
        TextSizeRow(
            textSize = 80.0F,
            onTextSizeUpdate = {}
        )
    }
}
