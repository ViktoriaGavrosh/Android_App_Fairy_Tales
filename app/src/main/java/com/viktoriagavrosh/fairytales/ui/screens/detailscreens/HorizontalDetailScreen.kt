package com.viktoriagavrosh.fairytales.ui.screens.detailscreens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.viktoriagavrosh.fairytales.R
import com.viktoriagavrosh.fairytales.model.FolkWork

@Composable
fun HorizontalDetailScreen(
    folkWork: FolkWork,
    isPuzzleType: Boolean,
    modifier: Modifier = Modifier
) {
    var bigCard by remember {
        mutableStateOf(false)
    }

    // TODO my add screen
    Text(
        text = folkWork.text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.padding_small)
            )
    )
}