package com.viktoriagavrosh.addtale

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.addtale.elements.HorizontalAddTaleContent
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.addtale.utils.TaleGenre
import com.viktoriagavrosh.addtale.utils.onNodeWithTagById
import com.viktoriagavrosh.addtale.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class HorizontalAddTaleContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun addTaleContent_horizontalScreen_titleTextFieldIsDisplayed() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_title)
            .assertExists("No title textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_titleTextFieldShowInputText() {
        val inputText = "Text"
        setHorizontalContent(NewTale(title = inputText))
        composeTestRule.onNodeWithText(inputText)
            .assertExists("No inputText on title textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_textTextFieldIsDisplayed() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.textfield_for_taletext_test_tag)
            .assertExists("No text textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_textTextFieldShowInputText() {
        val inputText = "Text"
        setHorizontalContent(NewTale(text = inputText))
        composeTestRule.onNodeWithText(inputText)
            .assertExists("No inputText on text textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_genreMenuWithAnimalTaleIsDisplayed() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithText("Казка пра жывёл")
            .assertExists("No animal tale on genreMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_genreMenuWithFairyTaleIsDisplayed() {
        setHorizontalContent(NewTale(taleGenre = TaleGenre.Fairy))
        composeTestRule.onNodeWithText("Чаро\u045Eная казка")
            .assertExists("No fairy tale on genreMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_genreMenuWithPeopleTaleIsDisplayed() {
        setHorizontalContent(NewTale(taleGenre = TaleGenre.People))
        composeTestRule.onNodeWithText("Бытавая казка")
            .assertExists("No people tale on genreMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_nightTitleIsDisplayed() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.night)
            .assertExists("No night title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_nightSwitchIsDisplayed() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .assertExists("No night switch")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_nightSwitchIsOff() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .assertIsOff()
    }

    @Test
    fun addTaleContent_horizontalScreen_nightSwitchIsOn() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .performClick()
            .assertIsOn()
    }

    @Test
    fun addTaleContent_horizontalScreen_addButtonIsDisplayed() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertExists("No add button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_horizontalScreen_addButtonIsEnabled() {
        setHorizontalContent(tale = NewTale(), isTaleValid = true)
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertIsEnabled()
    }

    @Test
    fun addTaleContent_horizontalScreen_addButtonIsNotEnabled() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertIsNotEnabled()
    }

    @Test
    fun addTaleContent_horizontalScreen_addButtonSizeValid() {
        setHorizontalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setHorizontalContent(tale: NewTale, isTaleValid: Boolean = false) {
        composeTestRule.setContent {
            FairyTalesTheme {
                HorizontalAddTaleContent(
                    taleProvider = { tale },
                    isTaleValidProvider = { isTaleValid },
                    onTitleValueChange = {},
                    onTextValueChange = {},
                    onGenreValueChange = {},
                    onIsNightValueChange = {},
                    onAddButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
