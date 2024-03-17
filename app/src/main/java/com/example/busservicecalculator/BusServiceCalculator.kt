package com.example.busservicecalculator
class BusServiceCalculator {
    companion object {
        const val DIAGNOSTIC_PRICE_A21 = 33
        const val DIAGNOSTIC_PRICE_A47 = 30
        const val DIAGNOSTIC_PRICE_18CM = 33 //TODO-Correct 18CM Price
        const val DIAGNOSTIC_PRICE_BMC = 30

        const val HOUR_PRICE_A21 = 37
        const val HOUR_PRICE_A47 = 37
        const val HOUR_PRICE_18CM = 37 //TODO-Correct 18CM Price
        const val HOUR_PRICE_BMC = 40
    }

    // Calculate payment for diagnostics based on bus type
    fun calculateDiagnosticPayment(amount: Int, busType: String): Int {
        return when (busType) {
            "MAN_21" -> amount * DIAGNOSTIC_PRICE_A21
            "MAN_A47" -> amount * DIAGNOSTIC_PRICE_A47
            "MAN_18CM" -> amount * DIAGNOSTIC_PRICE_18CM
            "BMC" -> amount * DIAGNOSTIC_PRICE_BMC
            else -> 0
        }
    }

    // Calculate payment for hours based on bus type
    fun calculateHourlyPayment(hours: Int, busType: String): Int {
        return when (busType) {
            "MAN_21" -> hours * HOUR_PRICE_A21
            "MAN_A47" -> hours * HOUR_PRICE_A47
            "MAN_18CM" -> hours * HOUR_PRICE_18CM
            "BMC" -> hours * HOUR_PRICE_BMC
            else -> 0
        }
    }
}