package com.example.samplenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.samplenavigation.ui.theme.SampleNavigationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleNavigationTheme {
                NavGraph()
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object Search : Screen("search/{query}") {
        fun createRoute(query: String?): String = "search/$query"
        fun getArguments(): List<NamedNavArgument> = listOf(
            navArgument("query") {
                nullable = true
                type = NavType.StringType
                defaultValue = null
            }
        )
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    AppState(navController)
}

class AppState(val navController: NavHostController) {
    fun navigateToSearch(query: String?) {
        navController.navigate(Screen.Search.createRoute(query))
    }

    fun navigateToSearchSingleTop(query: String?) {
        navController.navigate(Screen.Search.createRoute(query)){
            launchSingleTop = true
        }
    }
}

@Composable
fun NavGraph(
    appState: AppState = rememberAppState(),
    startDestination: String = Screen.Search.route,
) {

    NavHost(navController = appState.navController, startDestination = startDestination) {
        composable(route = Screen.Search.route, arguments = Screen.Search.getArguments()) {
            SearchScreen(
                onClickNavigate = appState::navigateToSearch,
                onClickSingleTop = appState::navigateToSearchSingleTop,
            )
        }
    }
}