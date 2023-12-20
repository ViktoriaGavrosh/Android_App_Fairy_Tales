package com.viktoriagavrosh.fairytales.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.fairytales.data.CatalogFairyTales
import com.viktoriagavrosh.fairytales.data.CompositionType
import com.viktoriagavrosh.fairytales.model.Composition
import com.viktoriagavrosh.fairytales.ui.theme.FairyTalesTheme
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesContentType
import com.viktoriagavrosh.fairytales.ui.utils.FairyTalesNavigationType

@Composable
fun ListAndDetailScreen(
    currentCompositionType: CompositionType,
    selectedComposition: Composition,
    navigationType: FairyTalesNavigationType,
    contentType: FairyTalesContentType,
    onCardClick: (Composition) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        ListCompositionsScreen(
            currentCompositionType = currentCompositionType,
            selectedComposition = selectedComposition,
            navigationType = navigationType,
            onCardClick = onCardClick,
            modifier = Modifier.weight(1F)
        )
        //val activity = LocalContext.current as Activity
        DetailScreen(                                                     //TODO
            currentCompositionType = currentCompositionType,
            selectedComposition = selectedComposition,
            contentType = contentType,
            onDetailScreenBackClick = {},   //{ activity.finish() },
            modifier = Modifier.weight(1F)
        )
    }
}

@Preview(widthDp = 1000)
@Composable
fun ListAndDetailScreenPreview() {
    FairyTalesTheme {
        ListAndDetailScreen(
            currentCompositionType = CompositionType.FairyTales,
            selectedComposition = CatalogFairyTales.fairyTales[0],
            navigationType = FairyTalesNavigationType.PERMANENT_NAVIGATION_DRAWER,
            contentType = FairyTalesContentType.LIST_AND_DETAILS,
            onCardClick = {}
        )
    }
}