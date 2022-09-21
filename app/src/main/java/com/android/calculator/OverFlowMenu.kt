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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OverFlowMenu(
    color : Color
) {
    // our compose state
    var showMenu by remember { mutableStateOf(false) }
    val listItems = arrayOf("Standard", "Scientific", "Settings")
    val contextTestForToast = LocalContext.current.applicationContext // this is probably how you reference a context in jetpack compose. I will understand later

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
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
            modifier = Modifier
                .background(Color.DarkGray)
        ) {

            listItems.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        when(itemIndex) {
                            0 -> Toast.makeText(contextTestForToast, "Standard item has been pressed.", Toast.LENGTH_LONG).show()
                            1 -> callScientific()
                            2 -> callSettings()
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

fun callSettings() {
    TODO("Implement the settings later. Maybe opening a PopupMenu.")
}

fun callScientific() {
    TODO("This function will be to call the scientific calculator by switching the fragment or the compose. ")
}