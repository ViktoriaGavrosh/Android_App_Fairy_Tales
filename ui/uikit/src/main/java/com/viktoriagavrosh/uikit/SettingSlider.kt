package com.viktoriagavrosh.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun SettingSlider(
    text: String,
    settingSizeProvider: () -> Float,
    onSettingSizeUpdate: (Float) -> Unit,
    testTag: String,
) {
    var sliderPosition by remember {
        mutableFloatStateOf(0.0F)
    }

    if (sliderPosition == 0.0F) sliderPosition = settingSizeProvider()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(250.dp)    // TODO 111
    ) {
        Box(
            modifier = Modifier.height(120.dp),   // TODO 111
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
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
                .testTag(testTag),
            valueRange = 8F..60F,
            onValueChangeFinished = { onSettingSizeUpdate(sliderPosition) }
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShortTitleCardPreview() {
    FairyTalesTheme {
        SettingSlider(
            text = "Setting",
            settingSizeProvider = { 24.0F },
            onSettingSizeUpdate = {},
            testTag = ""
        )
    }
}
