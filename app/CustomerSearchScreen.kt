package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomerSearchScreen(
    onCustomerSelected: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var customerName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }

    val isValid =
        customerName.trim().isNotEmpty() &&
        mobileNumber.length == 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "CUSTOMER REQUEST SEARCH",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = {
                customerName = it
            },
            label = {
                Text("Customer Name")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = mobileNumber,
            onValueChange = {
                if (
                    it.all { character -> character.isDigit() } &&
                    it.length <= 10
                ) {
                    mobileNumber = it
                }
            },
            label = {
                Text("Customer Mobile Number")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                onCustomerSelected(
                    customerName.trim(),
                    mobileNumber
                )
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SELECT CUSTOMER")
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
