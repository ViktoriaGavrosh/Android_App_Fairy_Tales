package com.viktoriagavrosh.fairytales.ui.elements

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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viktoriagavrosh.fairytales.R

/**
 * Composable that represent image
 */
@Composable
fun TaleImage(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    isBlur: Boolean = false,
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
                    radiusX = dimensionResource(id = R.dimen.blur_radius),
                    radiusY = dimensionResource(id = R.dimen.blur_radius),
                    edgeTreatment = BlurredEdgeTreatment(
                        RoundedCornerShape(dimensionResource(id = R.dimen.corner))
                    )
                )
        } else {
            modifier
                .aspectRatio(1.5F)
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner)))
        }
    )
}
