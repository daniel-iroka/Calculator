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

    val context = LocalContext.current.applicationContext
    val checkedState = remember { mutableStateOf(false) }

    // TODO - TEST("when I also come back, I will continue in the testing, checking and optimizing of the items here.")

    Surface(
        shape = Shapes.medium,
        color = Color.DarkGray
    ) {
        Box(
            // TODO - ADD A PADDING OF 18 DP HERE TO SEE HOW IT LOOKS.
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

/** NOTICE! This is was put in between the last two rows in this composable just in case I want to put it back. **/
//Spacer(modifier = Modifier.height(20.dp))