package com.android.calculator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.calculator.R
import com.android.calculator.ui.theme.Shapes

@Composable
fun CalculatorButton(
    symbol : String,
    color : Color = Color.White,
    modifier : Modifier,
    onCLick: () -> Unit
) {
    // This is a rounded Box here because of the clip CircleShape
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(Shapes.small)
            .clickable { onCLick() } // This will only make our 'Box' which is a text clickable
            .then(modifier)
    ) {

        Text(
            text = symbol,
            fontSize = 26.sp,
            color = color,
            style = TextStyle(
                fontWeight = Normal
            )
        )
    }
}

// Our CalculatorIcons

@Composable
fun CalculatorIcons(
    color : Color = Color.White,
    modifier: Modifier,
    onCLick: () -> Unit
) {

    val image : Painter = painterResource(id = R.drawable.ic_backspace)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(Shapes.small)
            .clickable { onCLick() }
            .then(modifier)

    ) {

        Image(
            painter = image,
            modifier = Modifier
                .requiredSize(26.dp),
            contentDescription = "BackSpace",
            colorFilter = ColorFilter.tint(color),
        )
    }
}