package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.githubsearchapp.presentation.searchScreen.state.DescriptionState

@Composable
fun RepositoryItemDescription(state: DescriptionState) {

    val dateTimeFieldModifier =
        Modifier
            .background(color = Color.White, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 4.dp)

    Column {
        UserItem(
            userState = state.author
        )
        ShadowWrapper(
            wrapperModifier = Modifier.padding(bottom = 10.dp),
            shadowModifier = Modifier.shadow(
                4.dp,
                shape = RoundedCornerShape(5.dp),
                clip = true
            ),
            content = { Text(text = "Создан: ${state.created}", modifier = dateTimeFieldModifier) }
        )
        ShadowWrapper(
            wrapperModifier = Modifier.padding(bottom = 10.dp),
            shadowModifier = Modifier.shadow(
                4.dp,
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
            Text(text = "Описание:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = state.description)
        }
    }

}

@Preview
@Composable
fun RepositoryItemDescriptionPreview() {
    RepositoryItemDescription(state = repositoryDescriptionState)
}
