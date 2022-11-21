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
    state : CalculatorState,
    modifier : Modifier
) {

    val verticalScroll = rememberScrollState()

    Box(
        modifier = modifier
            .verticalScroll(verticalScroll)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = state.time.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = Normal
                )

            // TODO - WHEN I COME BACK, I WILL CONTINUE FROM WHERE I STOPPED
            // TODO - WHICH IS BUILDING OUR 'HISTORY' COMPOSABLE OR FRAGMENT AND ADDING AN OVERFLOW MENU TO IT, WHICH WILL ALSO HAVE AN APP BAR.
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {

            CalculatorHistoryBox(
                valueInput = state.primaryTextState,
                valueResult = state.secondaryTextState,
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
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                text = valueInput,
                style = TextStyle(
                    fontWeight = Normal,
                    fontSize = 19.sp
                ),
                color = Color.White
            )

            Text(
                text = valueResult,
                style = TextStyle(
                    fontWeight = Normal,
                    fontSize = 19.sp
                ),
                color = Color.LightGray
            )
        }
    }
}




//
//Column(
//modifier = modifier
//.fillMaxWidth(),
//horizontalAlignment = Alignment.Start
//) {
//    Text(
//        text = time.toString(),
//        style = TextStyle(
//            fontSize = 16.sp,
//            fontWeight = Normal
//        )
//    )
//}
//