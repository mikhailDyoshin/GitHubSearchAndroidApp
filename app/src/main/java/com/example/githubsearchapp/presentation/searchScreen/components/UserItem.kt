package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.githubsearchapp.R
import com.example.githubsearchapp.common.utils.openInBrowser
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState

@Composable
fun UserItem(userState: SearchListItemState.UserState, modifier: Modifier  = Modifier) {

    val context = LocalContext.current

    val cornerSize = 5.dp

    Column(Modifier.padding(bottom = 10.dp)) {
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(cornerSize), clip = true)
        ) {
            Row(
                modifier = modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .clickable {
                        openInBrowser(context = context, htmlURL = userState.htmlURL)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .weight(0.5f),
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
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_right_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .weight(0.5f)
                        .size(50.dp)
                        .padding(4.dp),
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
                htmlURL = ""
            )
        )
    }
}