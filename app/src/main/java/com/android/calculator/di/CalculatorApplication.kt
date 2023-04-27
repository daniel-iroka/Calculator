package com.android.calculator.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *   When using DI with Hilt, our project must have an 'Application' class where we annotate it with the "@HiltAndroidApp" annotation.
 *   This annotation will be responsible for generating all the Hilt annotations in our Project, making them ready for use.
 */

@HiltAndroidApp
class CalculatorApplication : Application()