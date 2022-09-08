package com.android.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp

/** This Composable will be for the Calculator Buttons that will have icons. **/


@Composable
fun CalculatorButtonIcons(
    imageVector : ImageVector,
    modifier : Modifier,
    onCLick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onCLick() }
            .then(modifier)
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}