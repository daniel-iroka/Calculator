package com.android.calculator.dialogs

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

@Composable
fun DialogContent(
    title : String,
    dialogState : MutableState<Boolean>,
    dismissButtonText : String,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current.applicationContext

    // TODO - WHEN I COME BACK TOMORROW, I WILL CONTINUE DESIGNING THE BOX TO MAKE IT LOOK MORE IDEAL AND OKAY.

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
                        text = "Dark Mode/ Light Mode",
                        color = Color.White,
                        fontStyle = FontStyle.Normal
                    )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Other Settings",
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
