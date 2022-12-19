package com.android.calculator.ui

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.android.calculator.CalculatorAction
import com.android.calculator.R
import com.android.calculator.dialogs.SettingsDialog

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OverFlowMenu(
    color : Color,
    navController: NavHostController
) {
    // our compose state
    var showOvfMenu by remember { mutableStateOf(false) }
    val dialogState = remember { mutableStateOf(false) }
    val listItems = arrayOf("Standard", "Scientific", "Settings")

    Box(
        contentAlignment = Alignment.Center
    ) {
        // We added this here to reduce the default padding that surrounds an icon
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            IconButton(onClick = {
                showOvfMenu = !showOvfMenu
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
            dialogState = dialogState,
            navController = navController
        )

        DropdownMenu(
            expanded = showOvfMenu,
            onDismissRequest = { showOvfMenu = false },
            modifier = Modifier
                .background(DarkGray)
        ) {

            listItems.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        when(itemIndex) {
                            0 -> { navController.navigate("main_screen") }
                            1 -> { navController.navigate("first_screen") }
                            2 -> { dialogState.value = true }
                        }
                        showOvfMenu = false
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryOverFlowMenu(
    color : Color,
    onAction : (CalculatorAction) -> Unit
) {

    var showHistMenu by remember{ mutableStateOf(false)}
    val listItem = listOf("Clear")

    Box(
        contentAlignment = Alignment.Center
    ) {

        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            IconButton(onClick = {
                showHistMenu = !showHistMenu
            }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = stringResource(R.string.more),
                    tint = color
                )
            }
        }

        DropdownMenu(
            expanded = showHistMenu ,
            onDismissRequest = { showHistMenu = false },
            modifier = Modifier
                .background(DarkGray)
        ) {

            listItem.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        when(itemIndex) {
                            0 -> onAction(CalculatorAction.ClearHistory)
                        }
                        showHistMenu = false
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