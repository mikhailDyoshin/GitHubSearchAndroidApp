package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.githubsearchapp.R
import com.example.githubsearchapp.common.utils.openUserProfileInBrowser
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.ui.theme.ScoreColor

@Composable
fun UserItem(userState: SearchListItemState.UserState) {

    val context = LocalContext.current

    val cornerSize = 5.dp

    Column(Modifier.padding(bottom = 10.dp)) {
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(cornerSize), clip = true)
        ) {
            Row(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(cornerSize))
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .clickable {
                        openUserProfileInBrowser(context = context, htmlURL = userState.htmlURL)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = userState.avatarURL,
                        contentDescription = null,
                        modifier = Modifier
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            ).height(70.dp)
//                            .fillMaxWidth()
//                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp)),
                        placeholder = painterResource(id = R.drawable.user_icon)

                    )
                }

                Text(
                    text = userState.name,
                    maxLines = 1,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = userState.score.toString(),
                    fontSize = 20.sp,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = ScoreColor,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

@Preview
@Composable
fun UserItemPreview() {
    Column(modifier = Modifier.background(color = Color.White)) {
        UserItem(
            userState = SearchListItemState.UserState(
                name = "John Malkovich",
                avatarURL = "https://avatars.githubusercontent.com/u/65956?v=4",
                score = 1.0f,
                htmlURL = ""
            )
        )
    }
}