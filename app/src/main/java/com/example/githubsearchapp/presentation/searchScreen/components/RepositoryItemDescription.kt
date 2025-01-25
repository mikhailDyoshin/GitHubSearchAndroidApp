package com.example.githubsearchapp.presentation.searchScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.presentation.common.ShadowWrapper
import com.example.githubsearchapp.presentation.common.previewData.repositoryDescriptionState
import com.example.githubsearchapp.presentation.common.utils.setColorByTheme
import com.example.githubsearchapp.presentation.common.utils.setShadowElevationByTheme
import com.example.githubsearchapp.presentation.searchScreen.state.DescriptionState
import com.example.githubsearchapp.ui.theme.DarkThemeDetailsBackgroundColor
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme
import com.example.githubsearchapp.ui.theme.LightThemeDetailsBackgroundColor

@Composable
fun RepositoryItemDescription(state: DescriptionState) {

    val userItemBackgroundColor = setColorByTheme(
        lightThemeColor = LightThemeDetailsBackgroundColor,
        darkThemeColor = DarkThemeDetailsBackgroundColor
    )

    val dateTimeFieldModifier =
        Modifier
            .background(color = userItemBackgroundColor, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 4.dp)

    Column {
        UserItem(
            userState = state.author,
            Modifier.background(color = userItemBackgroundColor, shape = RoundedCornerShape(5.dp))
        )
        ShadowWrapper(
            wrapperModifier = Modifier.padding(bottom = 10.dp),
            shadowModifier = Modifier.shadow(
                setShadowElevationByTheme(4.dp),
                shape = RoundedCornerShape(5.dp),
                clip = true
            ),
            content = { Text(text = "Создан: ${state.created}", modifier = dateTimeFieldModifier) }
        )
        ShadowWrapper(
            wrapperModifier = Modifier.padding(bottom = 10.dp),
            shadowModifier = Modifier.shadow(
                setShadowElevationByTheme(4.dp),
                shape = RoundedCornerShape(5.dp),
                clip = true
            ),
            content = {
                Text(
                    text = "Обновлен: ${state.updated}",
                    modifier = dateTimeFieldModifier
                )
            }
        )
        Column(modifier = Modifier.padding(top = 5.dp)) {
            Text(
                text = "Описание:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(text = state.description, color = MaterialTheme.colorScheme.secondary)
        }
    }

}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun RepositoryItemDescriptionPreview() {
    GitHubSearchAppTheme{
        RepositoryItemDescription(state = repositoryDescriptionState)
    }
}
