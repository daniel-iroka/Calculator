package com.android.calculator.utils

import com.android.calculator.db.entity.CalculatorEntity
import com.android.calculator.models.CalculatorHistoryState

// This file contains our converter function which will convert our CalculatorEntity object into CalculatorHistoryState

class CalculatorHistoryConverter {

    fun convert(calculatorEntity: CalculatorEntity) =
        CalculatorHistoryState(calculatorEntity.historyPrimaryState, calculatorEntity.historySecondaryState, calculatorEntity.time)

}

