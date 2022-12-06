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
import com.android.calculator.model.CalculatorHistoryState

@Composable
fun CalculatorHistory(
    /** NOTE! I will use this later. **/
    modifier : Modifier,
    state : CalculatorHistoryState,
    onAction : (CalculatorAction) -> Unit
) {

    val scrollState = rememberScrollState()

    // TODO - FIX("Fix the Positioning of the items in the history screen - (Revisit jetpack compose basics for assistance)")

    // TODO - WHEN I COME BACK, I WILL CONTINUE IN THE FIXING OF THIS THING, WHICH IS THE POSITIONING

    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)

    ) {

        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            HistoryOverFlowMenu(
                color = Color.LightGray,
                onAction = onAction
            )
        }

        Spacer(modifier = modifier.width(15.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = state.time,
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = Normal
                )
            )
        }

        Spacer(modifier = modifier.width(28.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {

            CalculatorHistoryBox(
                valueInput = state.primaryState,
                valueResult = state.secondaryState,
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
        modifier = modifier
    ) {

        // Our calculator Results
        Column(
            modifier = modifier,
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