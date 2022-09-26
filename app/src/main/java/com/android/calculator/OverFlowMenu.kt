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
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.android.calculator.dialogs.SettingsDialog

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OverFlowMenu(
    color : Color
) {
    // our compose state
    var showMenu by remember { mutableStateOf(false) }
    val dialogState = remember { mutableStateOf(false) }
    val listItems = arrayOf("Standard", "Scientific", "Settings")
    val context = LocalContext.current.applicationContext    // This is how we get reference to our context in jetpack compose for our Toast message

    Box(
        contentAlignment = Alignment.Center
    ) {
        // We added this here to reduce the default padding that surrounds an icon
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            IconButton(onClick = {
                showMenu = !showMenu
            }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = stringResource(R.string.more),
                    tint = color
                )
            }
        }

        // Our DropDown
        SettingsDialog(
            dialogState = dialogState
        )

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
            modifier = Modifier
                .background(DarkGray)
        ) {

            listItems.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        when(itemIndex) {
                            0 -> Toast.makeText(context, "Standard Calculator has been selected.", Toast.LENGTH_LONG).show()
                            1 -> Toast.makeText(context, "Scientific Calculator has been selected", Toast.LENGTH_LONG).show()
                            2 ->  { dialogState.value = true }
                        }

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
