package com.android.calculator.ui

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.calculator.*
import com.android.calculator.model.CalculatorState
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

    // will make our layout scrollable based on a scrollState
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
                color = Color.LightGray,
                navController
            )
        }

        // Column for the rest of our Calculator.
        Column(
            modifier = Modifier
                .fillMaxWidth()  // will take up the entire width of the screen
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {

            // text for the result of our Calculation.
            Text(
                text = state.primaryTextState,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),

                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 56.sp,
                    color = Color.White,
                ),
                maxLines = 1
            )

            Text(
                text = state.secondaryTextState,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),

                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 42.sp,
                    color = Color.LightGray,
                ),
                maxLines = 1
            )

            Divider(
                color = LightGray,
                thickness = 4.dp,
                modifier = Modifier
                    .padding(vertical = 6.dp)
            )

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
                        .aspectRatio(1f) // this makes whatever composable will call it on look Squared.
                        .weight(1f),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Modulo))
                    }
                )
                CalculatorIcons(
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