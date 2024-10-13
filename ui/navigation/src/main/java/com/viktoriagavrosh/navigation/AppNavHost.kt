package com.viktoriagavrosh.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.viktoriagavrosh.addtale.AddTaleScreen
import com.viktoriagavrosh.infomenu.InfoScreen
import com.viktoriagavrosh.librarymenu.LibraryScreen
import com.viktoriagavrosh.reader.ReaderScreen
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.riddle.RiddleScreen
import com.viktoriagavrosh.settings.SettingsScreen
import com.viktoriagavrosh.shelf.ShelfScreen
import com.viktoriagavrosh.startmenu.StartScreen

/**
 * Composable with navigation between app screens
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param modifier the modifier to be applied to the layout
 * @param navController the navController for this host
 */
@Composable
internal fun AppNavHost(
    isVerticalScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = StartMenu,
    ) {
        composable<StartMenu> {
            StartScreen(
                isVerticalScreen = isVerticalScreen,
                onFavoriteClick = { navController.navigate(Shelf(NavGenre.Favorite)) },
                onNightClick = { navController.navigate(Shelf(NavGenre.Night)) },
                onLibraryClick = { navController.navigate(LibraryMenu) },
                onLastTaleClick = { id: Int ->
                    navController.navigate(Reader(id, NavGenre.Fairy))
                },
                onRandomClick = { id: Int ->
                    navController.navigate(BookInfo(id, NavGenre.Fairy))
                },
                onSettingsClick = { navController.navigate(Settings) },
                modifier = modifier,
            )
        }
        composable<LibraryMenu> {
            LibraryScreen(
                isVerticalScreen = isVerticalScreen,
                onTaleClick = { navController.navigate(Shelf(NavGenre.Animal)) },
                onRiddleClick = { navController.navigate(Shelf(NavGenre.Riddle)) },
                onFolkClick = { navController.navigate(Shelf(NavGenre.Poem)) },
                onRandomClick = { id: Int ->
                    navController.navigate(BookInfo(id, NavGenre.Fairy))
                },
                onAddTaleClick = { navController.navigate(AddTale) },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable<Shelf> { backStackEntry ->
            val shelf = backStackEntry.toRoute<Shelf>()
            val genre = shelf.navGenre.genre
            ShelfScreen(
                genre = genre,
                isVerticalScreen = isVerticalScreen,
                onCardClick = { id ->
                    when (genre) {
                        is ShelfGenre.Riddles -> navController.navigate(Riddle(id))
                        is ShelfGenre.Folks -> navController.navigate(Reader(id, shelf.navGenre))
                        else -> navController.navigate(BookInfo(id, shelf.navGenre))
                    }
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable<BookInfo> { backStackEntry ->
            val info = backStackEntry.toRoute<BookInfo>()
            InfoScreen(
                taleId = info.bookId,
                isVerticalScreen = isVerticalScreen,
                onReadClick = { navController.navigate(Reader(info.bookId, info.navGenre)) },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable<Reader> { backStackEntry ->
            val reader = backStackEntry.toRoute<Reader>()
            ReaderScreen(
                bookId = reader.bookId,
                genre = reader.navGenre.genre,
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                onSettingsClick = { navController.navigate(Settings) },
                onInfoClick = { navController.navigate(BookInfo(reader.bookId, reader.navGenre)) },
                modifier = modifier,
            )
        }
        composable<Riddle> { backStackEntry ->
            val riddle = backStackEntry.toRoute<Riddle>()
            RiddleScreen(
                riddleId = riddle.riddleId,
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                onSettingsClick = { navController.navigate(Settings) },
                modifier = modifier,
            )
        }
        composable<Settings> {
            SettingsScreen(
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable<AddTale> {
            AddTaleScreen(
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
    }
}
