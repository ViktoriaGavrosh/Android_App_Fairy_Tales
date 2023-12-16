package com.viktoriagavrosh.fairytales.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales.fairyTales
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.model.Composition.*

@Composable
fun ListCompositions(
    compositions: List<Composition>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(compositions) { composition ->
            CardComposition(
                composition = composition,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
fun CardComposition(
    composition: Composition,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Text(        //TODO логика если нет названия сказки
                text = stringResource(id = composition.titleId),
                style = MaterialTheme.typography.displaySmall
            )
            Image(
                painter = painterResource(id = composition.imageId),
                contentDescription = stringResource(id = composition.titleId),
                modifier = Modifier
                    .fillMaxWidth()

                    /* это для размывания картинки
                    .blur(
                        radiusX = 10.dp,
                        radiusY = 10.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(16.dp))
                    )

                     */
                    .clip(
                        RoundedCornerShape(
                            dimensionResource(id = R.dimen.corner)
                        )
                    )
                    .clickable { bigCard = !bigCard },
                contentScale = ContentScale.Crop
            )
            if (bigCard) {
                TextFairyTale(
                    composition = composition
                )
            }
        }
    }
}


@Composable
fun TextFairyTale(
    composition: Composition,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = composition.textId),
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_medium)
            ),
            style = MaterialTheme.typography.bodyLarge
        )
        ReadButton(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ReadButton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = { /*TODO*/ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = stringResource(id = R.string.read)
            )
        }
    }
}

@Preview
@Composable
fun ListCompositionsPreview() {
    ListCompositions(compositions = CatalogFairyTales.fairyTales)
}