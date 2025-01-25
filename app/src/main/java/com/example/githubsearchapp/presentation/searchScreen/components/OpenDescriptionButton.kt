package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubsearchapp.R

@Composable
fun OpenDescriptionButton(showDescription: Boolean, openDescription: () -> Unit) {


    val rotationAngle by animateFloatAsState(
        targetValue = if (showDescription) 90f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "RotationAnimation"
    )

    Button(
        onClick = { openDescription() },
        modifier = Modifier.padding(bottom = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(size = 5.dp),
        elevation = null,
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Подробнее")
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_right_icon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(30.dp)
                    .padding(4.dp)
                    .rotate(rotationAngle),
            )
        }

    }
}

@Preview
@Composable
fun OpenDescriptionButtonPreview() {
    OpenDescriptionButton(showDescription = false) {  }
}

@Preview
@Composable
fun OpenDescriptionButtonDescriptionActivePreview() {
    OpenDescriptionButton(showDescription = true) {  }
}