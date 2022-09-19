package com.android.calculator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.android.calculator.ui.theme.MediumGray

@Composable
fun OverFlowMenu(
    modifier: Modifier,
    color : Color
) {
    // our compose state
    var showMenu by remember { mutableStateOf(false) }
    val listItems = arrayOf("Standard", "Scientific", "Settings")
    val contextTestForToast = LocalContext.current.applicationContext // this is probably how you reference a context in jetpack compose. I will understand later

    Box(
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = {
            showMenu = !showMenu
        }) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = stringResource(R.string.more),
                tint = color
            )
        }

        // Our DropDown
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
            modifier = Modifier
                .background(Color.DarkGray)
        ) {

            // TODO - WHEN I COME BACK, I WILL PROCEED WITH READJUSTING THE DROPDOWN MENU TO THE AN APPROPRIATE LOOKING POSITION AND IMPLEMENT THE CLICK ACTION FOR EACH OF-
            // TODO   THE ITEMS IN THE DROP DOWN MENU.

            listItems.forEachIndexed { _, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        Toast.makeText(contextTestForToast, "Item $itemValue has been clicked!", Toast.LENGTH_LONG).show()
                        showMenu = false
                    }
                ) {
                    Text(
                        text = itemValue,
                        color = Color.White
                    )
                }
            }
        }
    }
}
