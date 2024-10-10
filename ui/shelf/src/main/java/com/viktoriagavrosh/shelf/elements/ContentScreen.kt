package com.viktoriagavrosh.shelf.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.shelf.model.Book
import com.viktoriagavrosh.shelf.utils.Tabs
import com.viktoriagavrosh.uitheme.FairyTalesTheme

/**
 * Composable to display [Book] list or grid screen
 */
@Composable
internal fun ContentScreen(
    booksProvider: () -> List<Book>,
    selectedTabProvider: () -> Tabs,
    isVerticalScreen: Boolean,
    onCardClick: (Int) -> Unit,
    onTabClick: (Tabs) -> Unit,
    onBackClick: () -> Unit,
    onHeartClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedTab = selectedTabProvider()
    val isTabsShow = selectedTab is Tabs.TaleTab || selectedTab is Tabs.FolkTab
    val topBarTitle = stringResource(selectedTabProvider().textId)
    val isHeartShow = selectedTab is Tabs.TaleTab

    if (isVerticalScreen) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Bookshelf(
                booksProvider = booksProvider,
                topBarTitle = topBarTitle,
                isVerticalScreen = true,
                isHeartShow = isHeartShow,
                isBlurImages = selectedTab is Tabs.Riddle,
                onCardClick = onCardClick,
                onBackClick = onBackClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.weight(1F)
            )
            if (isTabsShow) {
                BottomTabBar(
                    selectedTabProvider = selectedTabProvider,
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    } else {
        Row(modifier = modifier) {
            if (isTabsShow) {
                LeftTabRail(
                    selectedTabProvider = selectedTabProvider,
                    onTabClick = onTabClick,
                    modifier = Modifier.fillMaxHeight()
                )
            }
            Bookshelf(
                booksProvider = booksProvider,
                topBarTitle = topBarTitle,
                isVerticalScreen = false,
                isHeartShow = isHeartShow,
                isBlurImages = selectedTab is Tabs.Riddle,
                onCardClick = onCardClick,
                onBackClick = onBackClick,
                onHeartClick = onHeartClick,
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TabsVerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Poem",
                        imageUrl = "",
                    )
                }
            },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            isVerticalScreen = true,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Tale",
                        imageUrl = "",
                    )
                }
            },
            selectedTabProvider = { Tabs.Night },
            isVerticalScreen = true,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TabsHorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Poem",
                        imageUrl = "",
                    )
                }
            },
            selectedTabProvider = { Tabs.FolkTab.Poem },
            isVerticalScreen = false,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}

@Preview(name = "Light", widthDp = 1000)
@Preview(name = "Dark", widthDp = 1000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HorizontalContentScreenPreview() {
    FairyTalesTheme {
        ContentScreen(
            booksProvider = {
                List(4) {
                    Book(
                        title = "Poem",
                        imageUrl = "",
                    )
                }
            },
            selectedTabProvider = { Tabs.Night },
            isVerticalScreen = false,
            onCardClick = {},
            onTabClick = {},
            onBackClick = {},
            onHeartClick = {},
        )
    }
}
