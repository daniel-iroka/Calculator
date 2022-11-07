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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.calculator.model.CalculatorState
import com.android.calculator.ui.theme.LightGray
import com.android.calculator.ui.theme.Orange

@Composable
fun ScientificCalculator(
    state : CalculatorState,
    modifier: Modifier,
    buttonSpacing : Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
    navController : NavHostController
)  {

    val scrollState = rememberScrollState()

    // TODO - WHEN I ALSO COME BACK AND I'M DONE WITH THE OTHER FIXES, I WILL PROCEEED WITH ADING SOME REDESIGN IDEAS TO MY APP

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
                navController = navController
            )
        }

        // Column for the rest of our Calculator.
        Column(
            modifier = Modifier
                .fillMaxWidth()  // this obviously will take up the entire width of the screen
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {

            // text for the result of our Calculations
            Text(
                text = state.primaryTextState,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
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
                    fontSize = 45.sp,
                    color = Color.LightGray,
                )
            )

            // This is our Divider
            Divider(
                color = LightGray,
                thickness = 4.dp,
                modifier = Modifier
                    .padding(vertical = 3.dp)
            )

            // First Scientific Symbols row.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ScientificCalculatorButton(
                    symbol = "sin",
                    color = Orange,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Sin))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "cos",
                    color = Orange,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Cos))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "tan",
                    color = Orange,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Tan))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "log",
                    color = Orange,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Log))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "ln",
                    color = Orange,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.In))
                    }
                )
            }


            // Second Scientific Symbols row..
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ScientificCalculatorButton(
                    symbol = "x!",
                    color = Orange,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Factorial))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "x²",
                    color = Orange,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Squared))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "√",
                    color = Orange,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.SquareRoot))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "1/x",
                    color = Orange,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Inv))
                    }
                )
                ScientificCalculatorButton(
                    symbol = "(  )",
                    color = Orange,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(50.dp)
                        .height(30.dp),

                    onCLick = {
                        onAction(CalculatorAction.Brackets)
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "%",
                    color = Color.White,
                    modifier = Modifier
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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
                        .width(70.dp)
                        .height(60.dp)
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