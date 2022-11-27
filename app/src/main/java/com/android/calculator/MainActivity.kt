package com.android.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.calculator.navigation.ComposeNavigation
import com.android.calculator.ui.theme.CalculatorTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {

               // TODO - NOTE( "THIS IS A MAJOR TASK THAT I WILL ACCOMPLISH LATER IN THIS PROJECT WHICH IS THE DESIGNING OF THE LANDSCAPE ADAPTIVE LAYOUT OF THIS APP ONLY IF IT'S POSSIBLE AND NOT TO DIFFICULT")

            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AppPreview() {

    // We are using a this because we want to add animation to our compose navigation.
    val navController = rememberAnimatedNavController()
    ComposeNavigation(
        navController = navController
    )
}
//Caused by: java.lang.NoSuchMethodError: No static method AnimatedContent(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V in class Landroidx/compose/animation/AnimatedContentKt; or its super classes (declaration of 'androidx.compose.animation.AnimatedContentKt' appears in base.apk)
//at com.google.accompanist.navigation.animation.AnimatedNavHostKt.AnimatedNavHost(AnimatedNavHost.kt:227)
//at com.google.accompanist.navigation.animation.AnimatedNavHostKt.AnimatedNavHost(AnimatedNavHost.kt:87)
//at com.android.calculator.navigation.ComposeNavigationKt.ComposeNavigation(ComposeNavigation.kt:28)
//at com.android.calculator.MainActivityKt.AppPreview(MainActivity.kt:35)
//... 69 more
