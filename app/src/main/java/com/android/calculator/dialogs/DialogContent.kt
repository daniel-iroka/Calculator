package com.android.calculator.dialogs

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.calculator.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogContent(
    title : String,
    dialogState : MutableState<Boolean>,
    dismissButtonText : String,
    modifier: Modifier = Modifier,
    navController : NavHostController
) {

    // TODO - WHENEVER I RESUME BACK THIS PROJECT, I HAVE A LIST OF THINGS TO DO TO FINALISE THIS PROJECT WHICH ARE AS FOLLOWS :
    /*
     *  Todo - First, I will see more ways I can optimize the look and Implementation of our ViewModel, seeing if I can reduce the Boilerplate code(Maybe add a separate class to handle the logic).
     *   Todo - Second, I will check for other bugs that I may not have discovered somewhere especially in the calculator Implementation scene.
     *    Todo - Third, I will cross-check the final look of the overall App, like the HistoryScreen for example.
     *     Todo - Fourth, I will lastly Implement the dark and light mode and then see how I can save the State of our CalculatorHistory over Process death maybe-
     *      using SharedPreferences to store the value.
     *
     *     Todo - Lastly, I will do thorough inspection of the App and check if they are other things that need to be added and then add them. Note that given my current level-
     *       of expertise, this project may not be as perfect as it needs to be but I will do my best. So help me God. End.
     */

    val context = LocalContext.current.applicationContext
    val checkedState = remember { mutableStateOf(false) }

    Surface(
        shape = Shapes.medium,
        color = Color.DarkGray
    ) {
        Box(
            modifier = Modifier
        ) {

            Column(
                modifier = Modifier
                    .padding(vertical = 6.dp)
            ) {

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 23.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Dark Mode",
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = Normal,
                            fontSize = 17.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {

                        Switch(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                Toast.makeText(context, "App theme has been changed.", Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "History",
                        color = Color.White,
                        modifier = Modifier.clickable {
                            navController.navigate("second_screen")
                            dialogState.value = false
                        },
                        style = TextStyle(
                            fontWeight = Normal,
                            fontSize = 17.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier
                            .clickable {
                                dialogState.value = false
                                Toast.makeText(context, "Dialog has been closed", Toast.LENGTH_LONG).show()
                            },
                        text = dismissButtonText,
                        style = TextStyle (
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Normal,
                            color = Color.Red
                        )
                    )
                }
            }
        }
    }
}