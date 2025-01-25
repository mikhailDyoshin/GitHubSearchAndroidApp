package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.R
import com.example.githubsearchapp.ui.theme.ErrorScreenBackgroundColor

@Composable
fun StartScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ErrorScreenBackgroundColor), // Semi-transparent background
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .widthIn(min = 300.dp, max = 400.dp)
                .padding(vertical = 60.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = context.resources.getString(R.string.start_screen_title),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(200.dp),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = context.resources.getString(R.string.start_screen_text),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen()
}