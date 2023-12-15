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
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FairyTale

@Composable
fun ListFairyTales(
    fairyTales: List<FairyTale>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(fairyTales) { fairyTale ->
            CardFairyTale(
                fairyTale = fairyTale,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
fun CardFairyTale(
    fairyTale: FairyTale,
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
            Text(
                text = stringResource(id = fairyTale.nameId),
                style = MaterialTheme.typography.displaySmall
            )
            Image(
                painter = painterResource(id = fairyTale.imageId),
                contentDescription = stringResource(id = fairyTale.nameId),
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
                    fairyTale = fairyTale
                )
            }
        }
    }
}

@Composable
fun TextFairyTale(
    fairyTale: FairyTale,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = fairyTale.shortTextId),
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
                painter = painterResource(id = R.drawable.ic_read),
                contentDescription = stringResource(id = R.string.read)
            )
        }
    }
}
