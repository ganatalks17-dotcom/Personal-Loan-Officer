package com.personal.loanofficer.calculator

enum class InsuranceOption {
    WITHOUT,
    DEFAULT,
    CUSTOM
}

data class LoanCharges(
    val disbursementAmount: Double,
    val processingFee: Double,
    val insurance: Double,
    val totalFinancedAmount: Double
)

fun calculateLoanCharges(
    disbursementAmount: Double,
    insuranceOption: InsuranceOption,
    customInsurance: Double = 0.0
): LoanCharges {

    require(disbursementAmount >= 1000.0) {
        "Disbursement amount must be at least ₹1,000."
    }

    require(disbursementAmount <= 5000000.0) {
        "Disbursement amount cannot exceed ₹50,00,000."
    }

    val processingFee =
        disbursementAmount * 4.13 / 100.0

    val insurance = when (insuranceOption) {

        InsuranceOption.WITHOUT -> {
            0.0
        }

        InsuranceOption.DEFAULT -> {
            if (disbursementAmount < 500000.0) {
                20000.0
            } else {
                24500.0
            }
        }

        InsuranceOption.CUSTOM -> {
            require(customInsurance >= 0.0) {
                "Custom insurance cannot be negative."
            }

            customInsurance
        }
    }

    val totalFinancedAmount =
        disbursementAmount +
        processingFee +
        insurance

    return LoanCharges(
        disbursementAmount = disbursementAmount,
        processingFee = processingFee,
        insurance = insurance,
        totalFinancedAmount = totalFinancedAmount
    )
}
