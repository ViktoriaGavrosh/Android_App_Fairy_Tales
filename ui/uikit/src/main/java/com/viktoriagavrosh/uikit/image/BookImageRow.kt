package com.viktoriagavrosh.uikit.image

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display image with spacers
 *
 * @param title Text used to describe what this image represents
 * @param imageUrl url of image
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun BookImageRow(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(1F))
        BookImage(
            title = title,
            imageUrl = imageUrl,
            modifier = Modifier.weight(6F)
        )
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookImageRowPreview() {
    FairyTalesTheme {
        BookImageRow(
            title = "Title",
            imageUrl = "",
        )
    }
}
