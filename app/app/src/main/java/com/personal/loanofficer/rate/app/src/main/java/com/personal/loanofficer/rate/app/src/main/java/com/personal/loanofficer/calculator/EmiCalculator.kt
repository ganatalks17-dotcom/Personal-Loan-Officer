package com.personal.loanofficer.calculator

import kotlin.math.pow

data class EmiResult(
    val principal: Double,
    val monthlyRate: Double,
    val tenureMonths: Int,
    val emi: Double,
    val totalPayment: Double,
    val totalInterest: Double,
    val effectiveAnnualRate: Double,
    val effectiveMonthlyRate: Double
)

fun calculateEmi(
    principal: Double,
    annualRate: Double,
    tenureMonths: Int
): EmiResult {

    require(principal > 0) {
        "Principal must be greater than zero."
    }

    require(tenureMonths > 0) {
        "Tenure must be greater than zero."
    }

    val monthlyRate = annualRate / 12.0 / 100.0

    val emi = if (annualRate == 0.0) {

        principal / tenureMonths

    } else {

        val factor = (1.0 + monthlyRate).pow(tenureMonths)

        principal * monthlyRate * factor / (factor - 1.0)
    }

    val totalPayment = emi * tenureMonths

    val totalInterest = totalPayment - principal

    val tenureYears = tenureMonths / 12.0

    val effectiveAnnualRate =
        if (tenureYears > 0) {
            totalInterest / principal / tenureYears * 100.0
        } else {
            0.0
        }

    val effectiveMonthlyRate =
        effectiveAnnualRate / 12.0

    return EmiResult(
        principal = principal,
        monthlyRate = monthlyRate,
        tenureMonths = tenureMonths,
        emi = emi,
        totalPayment = totalPayment,
        totalInterest = totalInterest,
        effectiveAnnualRate = effectiveAnnualRate,
        effectiveMonthlyRate = effectiveMonthlyRate
    )
}
