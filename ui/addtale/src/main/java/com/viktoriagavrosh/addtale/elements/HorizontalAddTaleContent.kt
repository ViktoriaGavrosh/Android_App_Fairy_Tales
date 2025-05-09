package com.viktoriagavrosh.addtale.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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

/**
 * Composable to display AddTaleScreen content (horizontal screen orientation)
 *
 * @param taleProvider provides instance [NewTale]
 * @param isTaleValidProvider provides boolean. If true, new tale can be saved.
 * @param onTitleValueChange callback that is executed when tale's title is changed
 * @param onTextValueChange callback that is executed when tale's text is changed
 * @param onGenreValueChange callback that is executed when tale's genre is changed
 * @param onIsNightValueChange callback that is executed when tale's isNight is changed
 * @param onAddButtonClick callback that is executed when add button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun HorizontalAddTaleContent(
    taleProvider: () -> NewTale,
    isTaleValidProvider: () -> Boolean,
    onTitleValueChange: (String) -> Unit,
    onTextValueChange: (String) -> Unit,
    onGenreValueChange: (String) -> Unit,
    onIsNightValueChange: (Boolean) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        LeftColumn(
            taleProvider = taleProvider,
            isTaleValidProvider = isTaleValidProvider,
            onTitleValueChange = onTitleValueChange,
            onGenreValueChange = onGenreValueChange,
            onIsNightValueChange = onIsNightValueChange,
            onAddButtonClick = onAddButtonClick,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1F),
        )
        RightColumn(
            taleProvider = taleProvider,
            onTextValueChange = onTextValueChange,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1F),
        )

    }
}

/**
 * Composable to display right part of horizontal screen
 *
 * @param taleProvider provides instance [NewTale]
 * @param onTextValueChange callback that is executed when tale's text is changed
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun RightColumn(
    taleProvider: () -> NewTale,
    onTextValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tale = taleProvider()

    Column(
        modifier = modifier,
    ) {
        AppTextField(
            text = tale.text,
            onValueChange = onTextValueChange,
            label = stringResource(R.string.add_tale_text),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.add_text_field_height))
                .testTag(stringResource(R.string.textfield_for_taletext_test_tag)),
        )
    }
}

/**
 * Composable to display left part of horizontal screen
 *
 * @param taleProvider provides instance [NewTale]
 * @param onTitleValueChange callback that is executed when tale's title is changed
 * @param onGenreValueChange callback that is executed when tale's genre is changed
 * @param onIsNightValueChange callback that is executed when tale's isNight is changed
 * @param onAddButtonClick callback that is executed when add button is clicked
 * @param isTaleValidProvider provides boolean. If true, new tale can be saved.
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun LeftColumn(
    taleProvider: () -> NewTale,
    onTitleValueChange: (String) -> Unit,
    onGenreValueChange: (String) -> Unit,
    onIsNightValueChange: (Boolean) -> Unit,
    onAddButtonClick: () -> Unit,
    isTaleValidProvider: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    val tale = taleProvider()

    Column(
        modifier = modifier,
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
        SwitchRow(
            text = stringResource(R.string.night),
            isChecked = tale.isNight,
            onValueChange = onIsNightValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(R.dimen.padding_large))
        )
        AddButton(
            onClick = onAddButtonClick,
            isTaleValidProvider = isTaleValidProvider,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentPreview() {
    FairyTalesTheme {
        HorizontalAddTaleContent(
            taleProvider = { NewTale() },
            isTaleValidProvider = { true },
            onTitleValueChange = {},
            onTextValueChange = {},
            onGenreValueChange = {},
            onIsNightValueChange = {},
            onAddButtonClick = {},
        )
    }
}

