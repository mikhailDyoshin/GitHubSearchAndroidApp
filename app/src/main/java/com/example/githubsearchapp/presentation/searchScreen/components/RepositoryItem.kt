package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.presentation.navigation.RepositoryScreenNavArg
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState

@Composable
fun RepositoryItem(
    state: SearchListItemState.RepositoryState,
    navigateToRepositoryContent: (RepositoryScreenNavArg) -> Unit,
) {

    val cornerSize = 5.dp

    Column(Modifier.padding(bottom = 10.dp)) {
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(cornerSize), clip = true)
                .clickable {
                    if (state.owner != null) {
                        navigateToRepositoryContent(
                            RepositoryScreenNavArg(
                                owner = state.owner,
                                repository = state.name,
                            )
                        )
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(cornerSize))
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.name,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = state.numberOfForks.toString(), fontSize = 16.sp, maxLines = 1)
                        Text(text = "Forks", fontSize = 16.sp)
                    }
                }
                Text(
                    text = state.description,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    fontSize = 20.sp
                )


            }
        }
    }


}

@Preview
@Composable
fun RepositoryItemPreview() {
    Column(modifier = Modifier.background(color = Color.White)) {
        RepositoryItem(
            state = SearchListItemState.RepositoryState(
                name = "Rust-Tutorial and another part of a very long title",
                description = "Repository to study Rust programming language",
                numberOfForks = 105689,
                owner = "John"
            ),
            navigateToRepositoryContent = {}
        )
    }
}