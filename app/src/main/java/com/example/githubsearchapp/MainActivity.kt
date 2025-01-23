package com.example.githubsearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.navigation.SearchScreen
import com.example.githubsearchapp.presentation.repositoryContentScreen.RepositoryContentScreen
import com.example.githubsearchapp.presentation.searchScreen.SearchScreen
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            GitHubSearchAppTheme {
                KoinAndroidContext {


                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = SearchScreen,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable<SearchScreen> {
                                SearchScreen(
                                    modifier = Modifier.padding(
                                        innerPadding
                                    ),
                                    navigateToRepositoryContent = { navController.navigate(route = it) },
                                )
                            }
                            composable<RepositoryScreenNavArg> { backStateEntry ->
                                val repositoryRoute: RepositoryScreenNavArg =
                                    backStateEntry.toRoute()
                                RepositoryContentScreen(
                                    navArg = repositoryRoute
                                )
                            }
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GitHubSearchAppTheme {
        Greeting("Android")
    }
}