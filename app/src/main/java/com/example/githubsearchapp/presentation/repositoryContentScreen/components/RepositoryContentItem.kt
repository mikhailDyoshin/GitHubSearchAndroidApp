package com.example.githubsearchapp.presentation.repositoryContentScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.R
import com.example.githubsearchapp.ui.theme.DirIconColor
import com.example.githubsearchapp.ui.theme.FileIconColor
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme

@Composable
fun RepositoryContentItem(
    iconId: Int,
    name: String,
    size: String?,
    iconColor: Color,
    onClick: () -> Unit
) {
    Column(
        Modifier
            .background(color = Color.Transparent)
            .clickable { onClick() }) {
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = iconId),
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(4.dp),
                )
                Text(
                    text = name,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 10.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            if (size != null) {
                Text(text = size, modifier = Modifier.padding(end = 10.dp), color = Color.LightGray)
            }
        }
        HorizontalDivider(thickness = 2.dp, color = Color.Gray)
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
fun RepositoryContentDirItemPreview() {
    GitHubSearchAppTheme {
        RepositoryContentItem(
            iconId = R.drawable.folder_icon,
            name = ".idea",
            size = null,
            iconColor = DirIconColor,
            onClick = {}
        )
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
fun RepositoryContentFileItemPreview() {
    GitHubSearchAppTheme {
        RepositoryContentItem(
            iconId = R.drawable.file_icon,
            name = ".gitignore",
            size = "13 MB",
            iconColor = FileIconColor,
            onClick = {})
    }

}