package com.example.githubsearchapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.data.SearchRepositoryImpl
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.presentation.navigation.SearchScreen
import com.example.githubsearchapp.presentation.searchScreen.SearchScreen
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.koinInject

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
                                    )
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