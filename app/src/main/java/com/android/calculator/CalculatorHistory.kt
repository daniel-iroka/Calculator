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
import com.android.calculator.ui.theme.LightGray

@Composable
fun CalculatorHistory(
    modifier : Modifier,
    state : CalculatorHistoryState,
    onAction : (CalculatorAction) -> Unit
) {

    val scrollState = rememberScrollState()

    /** IMPORTANT NOTE! THINGS TO ADD LATER - ADD A SUPPORT APP BAR, A BACK BUTTON AND TRY TO SEE IF I CAN MAKE THE SECOND ROW REPEAT ITSELF FOR EACH OPERATION HISTORY.**/

    // TODO - IMPLEMENTATION("When I come back later, the next feature I will proceed with adding is the App bar using 'supportAppBar' in jetpack compose.")

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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 60.dp)
            ) {
                Text(
                    text = state.time,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = Normal
                    ),
                    color = LightGray
                )
            }

            Spacer(modifier = Modifier.width(25.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CalculatorHistoryBox(
                    valueInput = state.primaryState,
                    valueResult = state.secondaryState,
                    modifier = Modifier
                        .aspectRatio(1f),
                )
            }
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
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = valueInput,
                style = TextStyle(
                    fontWeight = Normal,
                    fontSize = 34.sp
                ),
                color = Color.White
            )

            Text(
                text = valueResult,
                style = TextStyle(
                    fontWeight = Normal,
                    fontSize = 34.sp
                ),
                color = Color.LightGray
            )
        }
    }
}