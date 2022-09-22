package com.android.calculator.dialogs

import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.android.calculator.CalculatorState

@Composable
fun SettingsDialog(
    dialogState : MutableState<Boolean>
) {

    // we remember that state in compose is any value that changes overtime.
    // "remember" function just helps us tell compose that we have a value or a 'state' that should survive recomposition

    if (dialogState.value) {
        Dialog(
            onDismissRequest = {
                // This will dismiss the dialog when the user presses the back button
                dialogState.value =  false
            },
            content = {
                DialogContent(title = "Settings", dialogState = dialogState, dismissButtonText = "Close")
            },
            // set the properties to true to close the dialog when the back button or anywhere in the screen is clicked or pressed
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}