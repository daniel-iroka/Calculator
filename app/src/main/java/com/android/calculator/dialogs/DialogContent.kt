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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.calculator.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogContent(
    title : String,
    dialogState : MutableState<Boolean>,
    dismissButtonText : String,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current.applicationContext
    val checkedState = remember {
        mutableStateOf(false)
    }

    Surface(
        shape = Shapes.medium,
        color = Color.DarkGray
    ) {
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Dark Mode",
                        color = Color.White,
                        fontStyle = FontStyle.Normal
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {

                        Switch(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
                    }
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "History",
                        color = Color.White,
                        fontStyle = FontStyle.Normal
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

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