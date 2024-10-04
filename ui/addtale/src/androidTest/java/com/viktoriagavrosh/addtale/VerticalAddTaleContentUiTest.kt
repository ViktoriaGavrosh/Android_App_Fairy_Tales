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
import com.viktoriagavrosh.addtale.elements.VerticalAddTaleContent
import com.viktoriagavrosh.addtale.model.NewTale
import com.viktoriagavrosh.addtale.utils.TaleGenre
import com.viktoriagavrosh.addtale.utils.onNodeWithTagById
import com.viktoriagavrosh.addtale.utils.onNodeWithTextById
import com.viktoriagavrosh.uikit.R
import com.viktoriagavrosh.uitheme.FairyTalesTheme
import org.junit.Rule
import org.junit.Test

class VerticalAddTaleContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun addTaleContent_verticalScreen_titleTextFieldIsDisplayed() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_title)
            .assertExists("No title textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_titleTextFieldShowInputText() {
        val inputText = "Text"
        setVerticalContent(NewTale(title = inputText))
        composeTestRule.onNodeWithText(inputText)
            .assertExists("No inputText on title textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_textTextFieldIsDisplayed() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.textfield_for_taletext_test_tag)
            .assertExists("No text textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_textTextFieldShowInputText() {
        val inputText = "Text"
        setVerticalContent(NewTale(text = inputText))
        composeTestRule.onNodeWithText(inputText)
            .assertExists("No inputText on text textField")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_textTitleIsDisplayed() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_text)
            .assertExists("No textTitle")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_genreMenuWithAnimalTaleIsDisplayed() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithText("Казка пра жывёл")
            .assertExists("No animal tale on genreMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_genreMenuWithFairyTaleIsDisplayed() {
        setVerticalContent(NewTale(taleGenre = TaleGenre.Fairy))
        composeTestRule.onNodeWithText("Чаро\u045Eная казка")
            .assertExists("No fairy tale on genreMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_genreMenuWithPeopleTaleIsDisplayed() {
        setVerticalContent(NewTale(taleGenre = TaleGenre.People))
        composeTestRule.onNodeWithText("Бытавая казка")
            .assertExists("No people tale on genreMenu")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_nightTitleIsDisplayed() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.night)
            .assertExists("No night title")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_nightSwitchIsDisplayed() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .assertExists("No night switch")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_nightSwitchIsOff() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .assertIsOff()
    }

    @Test
    fun addTaleContent_verticalScreen_nightSwitchIsOn() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTagById(R.string.switch_test_tag)
            .performClick()
            .assertIsOn()
    }

    @Test
    fun addTaleContent_verticalScreen_addButtonIsDisplayed() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertExists("No add button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun addTaleContent_verticalScreen_addButtonIsEnabled() {
        setVerticalContent(tale = NewTale(), isTaleValid = true)
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertIsEnabled()
    }

    @Test
    fun addTaleContent_verticalScreen_addButtonIsNotEnabled() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertIsNotEnabled()
    }

    @Test
    fun addTaleContent_verticalScreen_addButtonSizeValid() {
        setVerticalContent(NewTale())
        composeTestRule.onNodeWithTextById(R.string.add_tale_button_text)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setVerticalContent(tale: NewTale, isTaleValid: Boolean = false) {
        composeTestRule.setContent {
            FairyTalesTheme {
                VerticalAddTaleContent(
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
