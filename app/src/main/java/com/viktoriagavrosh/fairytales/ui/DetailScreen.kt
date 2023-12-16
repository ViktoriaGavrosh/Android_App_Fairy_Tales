package com.viktoriagavrosh.fairytales.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme

@Composable
fun DetailScreen(
    currentItem: Composition,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Image(
            painter = painterResource(id = currentItem.imageId),
            contentDescription = stringResource(id = currentItem.titleId),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner))),
            contentScale = ContentScale.Crop
        )
        Text(  //TODO добавить, чтобы текст распределялся равномерно
            text = stringResource(id = currentItem.textId),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_small)
                )
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    FairyTalesTheme {
        DetailScreen(
            currentItem = CatalogFairyTales.fairyTales[0]
        )
    }
}