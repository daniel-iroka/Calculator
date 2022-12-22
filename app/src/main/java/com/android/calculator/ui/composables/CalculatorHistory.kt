package com.android.calculator.ui.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.calculator.CalculatorAction
import com.android.calculator.ui.HistoryOverFlowMenu
import com.android.calculator.model.CalculatorHistoryState

private const val DATE_FORMAT = "Todo - Add a Proper date format here later on with SimpleDateFormat!"
private const val TAG = "CalculatorHistory"

@Composable
fun CalculatorHistory(
    modifier : Modifier,
    state : CalculatorHistoryState,
    onAction : (CalculatorAction) -> Unit,
    navController: NavController
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current.applicationContext

    /** IMPORTANT NOTE! THINGS TO ADD LATER - I WILL TRY TO SEE IF I CAN MAKE THE SECOND ROW REPEAT ITSELF FOR EACH OPERATION HISTORY(Using LazyColumn) .
     *  ADDITIONAL NOTE! Also, as per Above I will I will add something(an icon or image and a text to indicate that there is no calculated History.)
     * **/

    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
    ) {

        TopAppBar(
            title = {
                Text(
                    text = "History",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                    Toast.makeText(context, "Back button has been clicked!", Toast.LENGTH_LONG)
                        .show()
                    Log.i(TAG, "This is our currentHistory State in our History Screen : ${state.historyPrimaryState} and ${state.historySecondaryState}")
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back one screen"
                    )
                }
            },
            actions = {
                HistoryOverFlowMenu(
                    color = Color.LightGray,
                    onAction = onAction
                )
            },
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 65.dp,
                    end = 4.dp
                ) // Or we can substitute .padding(vertical = ...) if we are just handling the Top of screen
                .padding(18.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = state.time,
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CalculatorHistoryBox(
                    valueInput = state.historyPrimaryState,
                    valueResult = state.historySecondaryState,
                    modifier = Modifier
                        .aspectRatio(1f),
                )
            }
        }
    }
}

@Composable
fun CalculatorHistoryBox(
    valueInput : String,
    valueResult : String,
    modifier: Modifier
) {

    Box(
        modifier = modifier
    ) {

        // Our calculator Results
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = valueInput,
                style = TextStyle(
                    fontSize = 35.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = SemiBold
                ),
                color = Color.White
            )

            Text(
                text = valueResult,
                style = TextStyle(
                    fontSize = 37.sp,
                    fontFamily = FontFamily.SansSerif
                ),
                color = Color.LightGray,
                maxLines = 1
            )
        }
    }
}