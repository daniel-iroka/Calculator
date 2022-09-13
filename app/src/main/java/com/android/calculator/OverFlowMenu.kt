package com.android.calculator

import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun OverFlowMenu(
    modifier: Modifier,
    color : Color
) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = {
        showMenu = !showMenu
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = stringResource(R.string.more),
            tint = color
        )
    }

    // TODO - WHEN I COME BACK, I WILL TRY TO FIX THE ISSUE OF THE CUSTOM OVERFLOW MENU NOT SHOWING ON THE RIGHT HAND SIDE AND WELL... TRY TO MAKE IT SHOW ON THE RIGHT HAND SIDE.

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {

    }
}
