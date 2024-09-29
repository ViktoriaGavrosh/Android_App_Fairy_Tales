package com.viktoriagavrosh.uikit.image

import android.content.res.Configuration
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
fun BookImage(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    isBlur: Boolean = false
) {
    AsyncImage(
        model = ImageRequest
            .Builder(context = LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = title,
        error = painterResource(id = R.drawable.error),
        placeholder = painterResource(id = R.drawable.placeholder),
        contentScale = ContentScale.Crop,
        modifier = if (isBlur) {
            modifier
                .aspectRatio(1.5F)
                .blur(
                    radiusX = 20.dp,
                    radiusY = 20.dp,
                    edgeTreatment = BlurredEdgeTreatment(
                        RoundedCornerShape(dimensionResource(id = R.dimen.corner))
                    )
                )
        } else {
            modifier
                .aspectRatio(1.5F)
                .clip(
                    RoundedCornerShape(
                        dimensionResource(id = R.dimen.corner)
                    )
                )
        }
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookImagePreview() {
    FairyTalesTheme {
        BookImage(
            title = "Title",
            imageUrl = "",
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BlurBookImagePreview() {
    FairyTalesTheme {
        BookImage(
            title = "Title",
            imageUrl = "",
            isBlur = true,
        )
    }
}
