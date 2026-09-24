package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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

        Button(
            onClick = {
                // EMI calculation will be added next.
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("CALCULATE EMI")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = onBack
            ) {
                Text("BACK")
            }
        }
    }
}
