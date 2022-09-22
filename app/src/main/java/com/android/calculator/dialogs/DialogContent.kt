package com.android.calculator.dialogs

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogContent(
    title : String,
    dialogState : MutableState<Boolean>,
    dismissButtonText : String,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current.applicationContext

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            modifier = modifier
        ) {
            // TODO - WHEN I COME BACK TOMORROW, I WILL CONTINUE IMPLEMENTING THIS BOX STRUCTURE TO MAKE THIS LOOK LIKE A PROPER DIALOG.
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = title,
                fontSize = 16.sp
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Dark Mode/ Light Mode")
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Other Settings")
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        dialogState.value = false
                        Toast.makeText(context, "Dialog has been closed", Toast.LENGTH_LONG).show()
                    },
                text = dismissButtonText,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                color = Color.Red,
            )
        }
    }
}
