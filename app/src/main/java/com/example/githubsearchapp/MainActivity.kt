package com.example.githubsearchapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.githubsearchapp.common.utils.openInBrowser
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.navigation.SearchScreen
import com.example.githubsearchapp.presentation.repositoryContentScreen.RepositoryContentScreen
import com.example.githubsearchapp.presentation.searchScreen.SearchScreen
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {

    private val context = this

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            GitHubSearchAppTheme {
                KoinAndroidContext {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = SearchScreen,
                        modifier = Modifier
                    ) {
                        composable<SearchScreen> {
                            SearchScreen(
                                navigateToRepositoryContent = { navController.navigate(route = it) },
                                modifier = Modifier,
                            )
                        }
                        composable<RepositoryScreenNavArg> { backStateEntry ->
                            val repositoryRoute: RepositoryScreenNavArg =
                                backStateEntry.toRoute()
                            RepositoryContentScreen(
                                navArg = repositoryRoute,
                                openDirectory = { navController.navigate(route = it) },
                                openFile = { openInBrowser(context = context, htmlURL = it) }
                            )
                        }
                    }
                }
            }
        }
    }
}
