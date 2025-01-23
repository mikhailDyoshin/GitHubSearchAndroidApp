package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubsearchapp.R
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.ui.theme.SearchBarBackgroundColor
import com.example.githubsearchapp.ui.theme.SearchButtonDisabledColor
import com.example.githubsearchapp.ui.theme.SearchButtonEnabledColor

@Composable
fun SearchField(
    text: String,
    status: Resource.Status,
    updateSearchInput: (searchInput: String) -> Unit,
    search: () -> Unit
) {

    val backgroundColor = SearchBarBackgroundColor

    val searchBarEnabled = when (status) {
        Resource.Status.SUCCESS -> true
        Resource.Status.ERROR -> true
        Resource.Status.LOADING -> false
    }

    val buttonEnabled = searchBarEnabled && text.length >= 3

    val buttonColor = if (buttonEnabled) SearchButtonEnabledColor else SearchButtonDisabledColor

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = text,
            onValueChange = {
                updateSearchInput(it)
            },
            label = { Text(text = "Enter keyword") },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(fraction = 0.84f),
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Gray,
                focusedTextColor = Color.Black,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                disabledContainerColor = backgroundColor,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                selectionColors = LocalTextSelectionColors.current,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    search()
                }
            ),
            enabled = searchBarEnabled,
        )

        IconButton(
            onClick = { search() },
            Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
                .fillMaxWidth(),
            colors = IconButtonColors(
                disabledContentColor = Color.Gray,
                disabledContainerColor = backgroundColor,
                contentColor = Color.Black,
                containerColor = backgroundColor
            ),
            enabled = buttonEnabled
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                contentDescription = "Search button icon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp),
                tint = buttonColor
            )
        }
    }
}

@Preview
@Composable
fun SearchFieldPreview() {
    SearchField(text = "Hello", status = Resource.Status.SUCCESS, updateSearchInput = {}) {

    }
}

@Preview
@Composable
fun SearchFieldDisabledPreview() {
    SearchField(text = "Hello", status = Resource.Status.LOADING, updateSearchInput = {}) {

    }
}