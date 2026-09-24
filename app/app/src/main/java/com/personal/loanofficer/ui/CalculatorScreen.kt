package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.personal.loanofficer.calculator.InsuranceOption
import com.personal.loanofficer.calculator.calculateEmi
import com.personal.loanofficer.calculator.calculateLoanCharges
import com.personal.loanofficer.rate.getInterestRate

@Composable
fun CalculatorScreen(
    customerName: String = "",
    customerMobile: String = "",
    onBack: () -> Unit
)
) {
    var amount by remember { mutableStateOf("") }
    var tenure by remember { mutableStateOf("48") }

    var customerType by remember { mutableStateOf("Salaried") }
    var category by remember { mutableStateOf("New") }

    var rateMode by remember { mutableStateOf("Automatic") }
    var manualRate by remember { mutableStateOf("") }

    var insuranceOption by remember {
        mutableStateOf(InsuranceOption.WITHOUT)
    }

    var customInsurance by remember { mutableStateOf("") }

    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "NORMAL EMI CALCULATOR",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Disbursement Amount") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        Text("Tenure (Months)")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            listOf(33, 36, 39, 42).forEach { months ->
                Button(
                    onClick = { tenure = months.toString() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(months.toString())
                }
            }
        }

        Spacer(Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            listOf(48, 51, 63).forEach { months ->
                Button(
                    onClick = { tenure = months.toString() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(months.toString())
                }
            }
        }

        OutlinedTextField(
            value = tenure,
            onValueChange = { tenure = it },
            label = { Text("Custom Tenure") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        Text("Customer Type")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { customerType = "Salaried" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Salaried")
            }

            Button(
                onClick = { customerType = "Self Employed" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Self Employed")
            }
        }

        Spacer(Modifier.height(12.dp))

        Text("Loan Category")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { category = "New" },
                modifier = Modifier.weight(1f)
            ) {
                Text("New")
            }

            Button(
                onClick = { category = "PLTB" },
                modifier = Modifier.weight(1f)
            ) {
                Text("PLTB")
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Interest Rate")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { rateMode = "Automatic" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Automatic")
            }

            Button(
                onClick = { rateMode = "Manual" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Manual")
            }
        }

        if (rateMode == "Manual") {

            OutlinedTextField(
                value = manualRate,
                onValueChange = { manualRate = it },
                label = { Text("Annual Rate (%)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                singleLine = true
            )
        }

        Spacer(Modifier.height(16.dp))

        Text("Insurance")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Button(
                onClick = {
                    insuranceOption = InsuranceOption.WITHOUT
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Without")
            }

            Button(
                onClick = {
                    insuranceOption = InsuranceOption.DEFAULT
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Default")
            }

            Button(
                onClick = {
                    insuranceOption = InsuranceOption.CUSTOM
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Custom")
            }
        }

        if (insuranceOption == InsuranceOption.CUSTOM) {

            OutlinedTextField(
                value = customInsurance,
                onValueChange = { customInsurance = it },
                label = { Text("Custom Insurance ₹") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                singleLine = true
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {

                val loanAmount = amount.toDoubleOrNull()
                val months = tenure.toIntOrNull()

                if (loanAmount == null ||
                    loanAmount < 1000 ||
                    loanAmount > 5000000
                ) {
                    resultText = "Enter amount between ₹1,000 and ₹50,00,000."
                    return@Button
                }

                if (months == null || months <= 0) {
                    resultText = "Enter a valid tenure."
                    return@Button
                }

                val annualRate =
                    if (rateMode == "Automatic") {
                        getInterestRate(
                            amount = loanAmount.toInt(),
                            customerType = customerType,
                            category = category
                        )
                    } else {
                        manualRate.toDoubleOrNull()
                    }

                if (annualRate == null) {
                    resultText =
                        "Interest rate is not available for this amount and selection."
                    return@Button
                }

                val charges = try {
                    calculateLoanCharges(
                        disbursementAmount = loanAmount,
                        insuranceOption = insuranceOption,
                        customInsurance =
                            customInsurance.toDoubleOrNull() ?: 0.0
                    )
                } catch (e: Exception) {
                    resultText = e.message ?: "Invalid charges."
                    return@Button
                }

                val emiResult = calculateEmi(
                    principal = charges.totalFinancedAmount,
                    annualRate = annualRate,
                    tenureMonths = months
                )

                resultText = """
                    Disbursement: ₹%.2f
                    Interest Rate: %.2f%%
                    Processing Fee: ₹%.2f
                    Insurance: ₹%.2f
                    Total Financed: ₹%.2f
                    
                    Monthly EMI: ₹%.2f
                    Total Interest: ₹%.2f
                """.trimIndent().format(
                    charges.disbursementAmount,
                    annualRate,
                    charges.processingFee,
                    charges.insurance,
                    charges.totalFinancedAmount,
                    emiResult.emi,
                    emiResult.totalInterest
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CALCULATE EMI")
        }

        Spacer(Modifier.height(16.dp))

        if (resultText.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = resultText,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BACK")
        }
    }
}
