package com.viktoriagavrosh.addtale.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.addtale.utils.TaleGenre
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uikit.SelectionDropdownMenu
import com.viktoriagavrosh.uikit.SwitchRow
import com.viktoriagavrosh.uikit.text.AppTextField
import com.viktoriagavrosh.uitheme.FairyTalesTheme

@Composable
internal fun VerticalAddTaleContent(
    taleProvider: () -> NewTale,
    isTaleValidProvider: () -> Boolean,
    onTitleValueChange: (String) -> Unit,
    onTextValueChange: (String) -> Unit,
    onGenreValueChange: (String) -> Unit,
    onIsNightValueChange: (Boolean) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tale = taleProvider()
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        AppTextField(
            text = tale.title,
            onValueChange = onTitleValueChange,
            label = stringResource(R.string.add_tale_title),
            modifier = Modifier
                .fillMaxWidth(),
        )
        SelectionDropdownMenu(
            options = TaleGenre.entries.map { it.title },
            selectedOption = tale.taleGenre.title,
            onValueChange = onGenreValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_medium)),
        )
        Text(
            text = stringResource(R.string.add_tale_text),
            style = MaterialTheme.typography.titleLarge,
        )
        AppTextField(
            text = tale.text,
            onValueChange = onTextValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.add_text_field_height)),
        )
        SwitchRow(
            text = stringResource(R.string.night),
            isChecked = tale.isNight,
            onValueChange = onIsNightValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(R.dimen.padding_large))
        )
        AddButton(
            onAddButtonClick = onAddButtonClick,
            isTaleValidProvider = isTaleValidProvider,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentPreview() {
    FairyTalesTheme {
        VerticalAddTaleContent(
            taleProvider = { NewTale() },
            isTaleValidProvider = { true },
            onTitleValueChange = {},
            onTextValueChange = {},
            onGenreValueChange = {},
            onIsNightValueChange = {},
            onAddButtonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
