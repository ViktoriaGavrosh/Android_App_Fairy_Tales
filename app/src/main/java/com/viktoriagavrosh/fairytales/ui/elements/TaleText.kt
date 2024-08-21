package com.viktoriagavrosh.fairytales.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.fairytales.model.TaleUi
import com.viktoriagavrosh.fairytales.ui.detailscreen.TextDetail

@Composable
fun TaleText(
    tale: TaleUi,
    fontSize: Float,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        if (tale.genre == Genre.Story) {
            TextDetail(
                text = tale.text,
                fontSize = fontSize,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Spacer(modifier = Modifier.weight(1F))
            TextDetail(
                text = tale.text,
                fontSize = fontSize,
            )
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}
