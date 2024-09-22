package com.viktoriagavrosh.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.viktoriagavrosh.read.ReadScreen
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import com.viktoriagavrosh.riddle.RiddleScreen
import com.viktoriagavrosh.settings.SettingsScreen
import com.viktoriagavrosh.shelf.ShelfScreen
import com.viktoriagavrosh.startmenu.StartScreen

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
                    navController.navigate(Reader(id, NavGenre.Fairy))
                },
                onSettingsClick = { navController.navigate(Settings) },
                modifier = modifier,
            )
        }
        composable<LibraryMenu> {
            Column(modifier = modifier) { Text("Hi") }   // TODO заглушка
        // TODO 111
            /*
            LibraryScreen(
                isVerticalScreen = isVerticalScreen,
                onTaleClick = { navController.navigate(Shelf(ShelfGenre.Tales.Fairy)) },
                onRiddleClick = { navController.navigate(Shelf(ShelfGenre.Riddle)) },
                onFolkClick = { navController.navigate(Shelf(ShelfGenre.Folk.Poem) },
                onRandomClick = { id: Int ->
                    navController.navigate(Read(id, ShelfGenre.Tales.Fairy))
                },
                onAddTaleClick = { navController.navigate(AddTale) },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
             */
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
                        is ShelfGenre.Tales -> navController.navigate(Info(id, shelf.navGenre))
                        else -> navController.navigate(Reader(id, shelf.navGenre))
                    }
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable<Info> {
            Column(modifier = modifier) { Text("Hi") }   // TODO заглушка
            // TODO 111
            /*
            val info = backStackEntry.toRoute<Info>()
            InfoScreen(
                isVerticalScreen = isVerticalScreen,
                onReadClick = { navController.navigate(Read(info.id, info.genre)) }
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
             */
        }
        composable<Reader> { backStackEntry ->
            val reader = backStackEntry.toRoute<Reader>()
            ReadScreen(
                bookId = reader.bookId,
                genre = reader.navGenre.genre,
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                onSettingsClick = { navController.navigate(Settings) },
                onInfoClick = { navController.navigate(Info(reader.bookId, reader.navGenre)) },
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
            Column(modifier = modifier) { Text("Hi") }   // TODO заглушка
            // TODO 111
            /*
            AddTaleScreen(
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
             */
        }
    }

    /*
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.HomeScreen.screen,
    ) {
        composable(
            route = NavigationDestination.HomeScreen.screen
        ) {
            ShelfScreen(
                genreName = ShelfGenre.Tales.Fairy.genreName,  // TODO 111 заглушка
                isVerticalScreen = isVerticalScreen,
                onCardClick = { id ->
                    navController.navigate(
                        route = "${NavigationDestination.ReadScreen.screen}/${id}"
                    )
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationDestination.ReadScreen.screen}/{value}"
        ) { backStackEntry ->
            val taleId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            ReadScreen(
                bookId = taleId,
                genre = ShelfGenre.Nights,   // TODO  заглушка
                isVerticalScreen = isVerticalScreen,
                onBackClick = {
                    navController.navigate(NavigationDestination.HomeScreen.screen)
                },
                onSettingsClick = {
                    navController.navigate(NavigationDestination.SettingsScreen.screen)
                },
                onInfoClick = {},   // TODO  заглушка
                modifier = modifier,
            )
        }
        composable(
            route = "${NavigationDestination.RiddleScreen.screen}/{value}"
        ) { backStackEntry ->
            val riddleId = backStackEntry.arguments?.getString("value")?.toInt() ?: 0
            RiddleScreen(
                riddleId = riddleId,
                isVerticalScreen = isVerticalScreen,
                onBackClick = {
                    navController.navigate(NavigationDestination.HomeScreen.screen)
                },
                onSettingsClick = {
                    navController.navigate(NavigationDestination.SettingsScreen.screen)
                },
                modifier = modifier,
            )
        }
        composable(
            route = NavigationDestination.SettingsScreen.screen
        ) {
            SettingsScreen(
                isVerticalScreen = isVerticalScreen,
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = modifier,
            )
        }
    }

     */
}

