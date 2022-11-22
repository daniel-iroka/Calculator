package com.android.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.calculator.model.CalculatorState

@Composable
fun CalculatorHistory(
    /** NOTE! I will use this later. **/
    state : CalculatorState,
    modifier : Modifier
) {

    val verticalScroll = rememberScrollState()

    // TODO - WHEN I COME BACK, I WILL CONTINUE IN THE BUILDING OF THIS THING.

    Box(
        modifier = modifier
            .verticalScroll(verticalScroll)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Test time.",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = Normal
                )
            )
        }

        // TODO - RUN THIS LATER WHEN I COME BACK WHICH IS TRY TO INCREASE THIS DISTANCE BETWEEN THIS TWO
        Spacer(modifier = modifier.width(28.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {

            CalculatorHistoryBox(
                valueInput = "First Text test.",
                valueResult = "Second Text test.",
                modifier = Modifier
                    .aspectRatio(1f)
            )
        }
    }
}

@Composable
fun CalculatorHistoryBox(
    valueInput : String,
    valueResult : String,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .then(modifier)
    ) {

        // Our calculator Results
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = valueInput,
                style = TextStyle(
                    fontWeight = Normal,
                    fontSize = 22.sp
                ),
                color = Color.White
            )

            Text(
                text = valueResult,
                style = TextStyle(
                    fontWeight = Normal,
                    fontSize = 22.sp
                ),
                color = Color.LightGray
            )
        }
    }
}