package com.personal.loanofficer.rate

fun getInterestRate(
    amount: Int,
    customerType: String,
    category: String
): Double? {

    val band = rateTable.firstOrNull {
        amount >= it.minAmount && amount <= it.maxAmount
    } ?: return null

    return when (customerType) {

        "Salaried" -> {
            when (category) {
                "New" -> band.salariedNew
                "PLTB" -> band.salariedPltb
                else -> null
            }
        }

        "Self Employed" -> {
            when (category) {
                "New" -> band.selfEmployedNew
                "PLTB" -> band.selfEmployedPltb
                else -> null
            }
        }

        else -> null
    }
}
