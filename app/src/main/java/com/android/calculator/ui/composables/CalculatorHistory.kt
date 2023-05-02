package com.android.calculator.ui.composables

import android.text.format.DateUtils
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.History
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.android.calculator.models.CalculatorHistoryState
import com.android.calculator.ui.theme.LightGray

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
                    top = 38.dp
                ) // Or we can substitute .padding(vertical = ...) if we are just handling the Top of screen.
                .padding(18.dp),
            verticalArrangement = Arrangement.Center
        ) {

            // This 'if' check will Display our History Lists or a "NoHistory" Text depending on if our lists is empty or not.
            if (state.isEmpty()) {
                Nohistory(
                    modifier = Modifier
                        .align(CenterHorizontally)
                )
            } else {
                HistoryList(dataList = state)
            }
        }
    }
}

@Composable
fun CalculatorHistory(
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
            
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun HistoryList(
    dataList: List<CalculatorHistoryState>
) {

    val lazyColumnState = rememberLazyListState()
    val context = LocalContext.current
    var time = ""

    // IMPORTANT _TODO - Later in this project, I will see if I can add this based on a complete Day and maybe draw a line after,

    if (dataList.isNotEmpty()) {
        val stateTime = dataList.first()
        time = DateUtils.getRelativeDateTimeString(context, stateTime.time.time, DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0).toString()
    }

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
                    .padding(top = 10.dp, end = 4.dp)
            ) {
                Text(
                    text = time,
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                    color = Color.LightGray
                )
            }
        }

        items(dataList) { state ->
            CalculatorHistory(state = state)
        }
    }
}

@Composable
fun Nohistory(
    modifier : Modifier
) {

    Box(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier,
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Icon(
                imageVector = Icons.Outlined.History,
                contentDescription = "Empty Calculator History",
                modifier = Modifier
                    .size(25.dp),
                tint = LightGray
            )

            Text(
                text = "No history",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif
                ),
                color = Color.LightGray
            )
        }
    }
}