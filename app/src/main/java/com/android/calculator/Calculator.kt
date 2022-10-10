package com.android.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.calculator.ui.theme.LightGray
import com.android.calculator.ui.theme.Orange

/**  This is our Calculator file. This is the Compose file that Implements the look of our Calculator App, taking in other classes and functionalities like the state and-
 *   so on.**/


@Composable
fun Calculator(
    state : CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing : Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
    navController: NavHostController
) {

    // this will make whatever layout component we modify it scrollable based on the scrollState
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
        .verticalScroll(state = scrollState)
    ) {

        // Column for our OverFlowMenu
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            OverFlowMenu(
                color = LightGray,
                navController
            )
        }

        // Column for the rest of our Calculator.
        Column(
            modifier = Modifier
                .fillMaxWidth()  // this obviously will take up the entire width of the screen
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {

            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = Color.White,
                maxLines = 2
            )

            Divider(
                color = LightGray,
                thickness = 4.dp,
                modifier = Modifier
                    .padding(vertical = 6.dp)
            )

            /** IMPORTANT NOTE : I WILL ADD BACKGROUND(RoundedCornerShape) LATER WHEN I DECIDE IF I WANT TO CHANGE THE STYLE OF THE BUTTONS IN THE CALCULATOR. **/

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "%",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f) // this makes whatever composable will call it on look Square.
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Modulo))
                    }
                )
                CalculatorButton(
                    symbol = "AC",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Clear)
                    }
                )
                CalculatorButton(
                    symbol = "Del",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Delete)
                    }
                )
                CalculatorButton(
                    symbol = "÷",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                )
            }


            // our Second Number ROW
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(7))
                    }
                )
                CalculatorButton(
                    symbol = "8",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(8))
                    }
                )
                CalculatorButton(
                    symbol = "9",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(9))
                    }
                )
                CalculatorButton(
                    symbol = "×",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                )
            }

            // our Third Number ROW
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(4))
                    }
                )
                CalculatorButton(
                    symbol = "5",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(5))
                    }
                )
                CalculatorButton(
                    symbol = "6",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(6))
                    }
                )
                CalculatorButton(
                    symbol = "-",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                )
            }

            // our Fourth Number ROW
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(1))
                    }
                )
                CalculatorButton(
                    symbol = "2",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(2))
                    }
                )
                CalculatorButton(
                    symbol = "3",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Number(3))
                    }
                )
                CalculatorButton(
                    symbol = "+",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    }
                )
            }

            // our Fifth Number ROW
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(LightGray)
                        .aspectRatio(2f)
                        .weight(2f),

                    onCLick = {
                        onAction(CalculatorAction.Number(0))
                    }
                )
                CalculatorButton(
                    symbol = ".",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Decimal)
                    }
                )
                CalculatorButton(
                    symbol = "=",
                    color = Color.White,
                    modifier = Modifier
                        .width(80.dp)
                        .height(65.dp)
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Calculate)
                    }
                )
            }
        }
    }
}