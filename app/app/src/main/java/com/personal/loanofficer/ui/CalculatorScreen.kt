package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.personal.loanofficer.calculator.InsuranceOption
import com.personal.loanofficer.calculator.calculateEmi
import com.personal.loanofficer.calculator.calculateLoanCharges
import com.personal.loanofficer.rate.getInterestRate
import java.util.Locale

@Composable
fun CalculatorScreen(
    onBack: () -> Unit
) {

    var amount by remember {
        mutableStateOf("")
    }

    var tenure by remember {
        mutableStateOf("48")
    }

    var customerType by remember {
        mutableStateOf("Salaried")
    }

    var category by remember {
        mutableStateOf("New")
    }

    var insurance by remember {
        mutableStateOf("Default")
    }

    var customInsurance by remember {
        mutableStateOf("")
    }

    var resultText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "NORMAL EMI CALCULATOR",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = amount,
            onValueChange = {
                if (it.all { character -> character.isDigit() }) {
                    amount = it
                }
            },
            label = {
                Text("Loan / Disbursement Amount")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            singleLine = true
        )

        Text(
            text = "Allowed amount: ₹1,000 – ₹50,00,000",
            modifier = Modifier.padding(top = 6.dp)
        )

        OutlinedTextField(
            value = tenure,
            onValueChange = {
                if (it.all { character -> character.isDigit() }) {
                    tenure = it
                }
            },
            label = {
                Text("Tenure in Months")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            singleLine = true
        )

        Text(
            text = "Presets: 33, 36, 39, 42, 48, 51, 63 months",
            modifier = Modifier.padding(top = 6.dp)
        )

        Text(
            text = "Customer Type",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            if (customerType == "Salaried") {
                Button(onClick = { customerType = "Salaried" }) {
                    Text("SALARIED")
                }
            } else {
                OutlinedButton(onClick = { customerType = "Salaried" }) {
                    Text("SALARIED")
                }
            }

            if (customerType == "Self Employed") {
                Button(onClick = { customerType = "Self Employed" }) {
                    Text("SELF EMPLOYED")
                }
            } else {
                OutlinedButton(onClick = { customerType = "Self Employed" }) {
                    Text("SELF EMPLOYED")
                }
            }
        }

        Text(
            text = "Loan Category",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            if (category == "New") {
                Button(onClick = { category = "New" }) {
                    Text("NEW")
                }
            } else {
                OutlinedButton(onClick = { category = "New" }) {
                    Text("NEW")
                }
            }

            if (category == "PLTB") {
                Button(onClick = { category = "PLTB" }) {
                    Text("PLTB")
                }
            } else {
                OutlinedButton(onClick = { category = "PLTB" }) {
                    Text("PLTB")
                }
            }
        }

        Text(
            text = "Insurance",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            if (insurance == "Without") {
                Button(onClick = { insurance = "Without" }) {
                    Text("WITHOUT")
                }
            } else {
                OutlinedButton(onClick = { insurance = "Without" }) {
                    Text("WITHOUT")
                }
            }

            if (insurance == "Default") {
                Button(onClick = { insurance = "Default" }) {
                    Text("DEFAULT")
                }
            } else {
                OutlinedButton(onClick = { insurance = "Default" }) {
                    Text("DEFAULT")
                }
            }

            if (insurance == "Custom") {
                Button(onClick = { insurance = "Custom" }) {
                    Text("CUSTOM")
                }
            } else {
                OutlinedButton(onClick = { insurance = "Custom" }) {
                    Text("CUSTOM")
                }
            }
        }

        if (insurance == "Custom") {

            OutlinedTextField(
                value = customInsurance,
                onValueChange = {
                    if (it.all { character -> character.isDigit() }) {
                        customInsurance = it
                    }
                },
                label = {
                    Text("Custom Insurance Amount")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                singleLine = true
            )
        }

        Button(
            onClick = {

                val disbursement = amount.toDoubleOrNull()
                val months = tenure.toIntOrNull()

                if (disbursement == null || months == null) {

                    resultText = "Please enter a valid amount and tenure."

                } else if (disbursement < 1000 ||
                    disbursement > 5000000
                ) {

                    resultText =
                        "Amount must be between ₹1,000 and ₹50,00,000."

                } else if (months <= 0) {

                    resultText =
                        "Tenure must be greater than zero."

                } else {

                    val rate = getInterestRate(
                        amount = disbursement.toInt(),
                        customerType = customerType,
                        category = category
                    )

                    if (rate == null) {

                        resultText =
                            "Interest rate is unavailable for this selection."

                    } else {

                        val insuranceOption =
                            when (insurance) {

                                "Without" ->
                                    InsuranceOption.WITHOUT

                                "Custom" ->
                                    InsuranceOption.CUSTOM

                                else ->
                                    InsuranceOption.DEFAULT
                            }

                        val customAmount =
                            customInsurance.toDoubleOrNull() ?: 0.0

                        val charges = calculateLoanCharges(
                            disbursementAmount = disbursement,
                            insuranceOption = insuranceOption,
                            customInsurance = customAmount
                        )

                        val emiResult = calculateEmi(
                            principal = charges.totalFinancedAmount,
                            annualRate = rate,
                            tenureMonths = months
                        )

                        resultText = String.format(
                            Locale.US,
                            "Interest Rate: %.2f%%\n" +
                                "Processing Fee: ₹%.2f\n" +
                                "Insurance: ₹%.2f\n" +
                                "Total Financed Amount: ₹%.2f\n" +
                                "Monthly EMI: ₹%.2f\n" +
                                "Total Interest: ₹%.2f",
                            rate,
                            charges.processingFee,
                            charges.insurance,
                            charges.totalFinancedAmount,
                            emiResult.emi,
                            emiResult.totalInterest
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("CALCULATE EMI")
        }

        if (resultText.isNotEmpty()) {

            Text(
                text = resultText,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 20.dp)
            )
        }

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text("BACK")
        }
    }
}
