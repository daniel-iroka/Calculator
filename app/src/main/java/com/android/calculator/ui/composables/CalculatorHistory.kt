package com.android.calculator.ui.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
    modifier: Modifier,
    state: List<CalculatorHistoryState>,
    onAction: (CalculatorAction) -> Unit,
    navController: NavController,
) {
    val context = LocalContext.current.applicationContext

    /*
     *  NOTE! Also, as per Above I will I will add something(an icon or image and a text to indicate that there is no calculated History.)
     */

    // Todo - IMPROVE("When I come back, what I will work on probably before the todo in the ViewModel is trying to Improve the look of our LazyColumn's list in our Composable")

    Box(
        modifier = modifier
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
                    navController.navigateUp()
                    Toast.makeText(context, "Back button has been clicked!", Toast.LENGTH_LONG)
                        .show()
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
                .fillMaxSize()
                .padding(
                    top = 65.dp,
                    end = 4.dp
                ) // Or we can substitute .padding(vertical = ...) if we are just handling the Top of screen
                .padding(18.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                
                HistoryList(dataList = state)

            }
        }
    }
}

@Composable
fun CalculatorHistoryBox(
    modifier: Modifier = Modifier,
    state: CalculatorHistoryState
) {

    Box(
        modifier = modifier
            .padding(end = 8.dp),
    ) {

        // Our calculator Results
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = state.historyPrimaryState,
                style = TextStyle(
                    fontSize = 35.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = SemiBold
                ),
                color = Color.White
            )

            Text(
                text = state.historySecondaryState,
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

@Composable
fun HistoryList(
    dataList: List<CalculatorHistoryState>
) {

    val lazyColumnState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyColumnState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = "3 Days ago",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                    color = Color.LightGray
                )
            }
        }

        items(dataList) { state ->
             CalculatorHistoryBox(state = state)
        }
    }
}