package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CallbackScreen(
    onBack: () -> Unit
) {
    var customerName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var callbackDate by remember { mutableStateOf("") }
    var callbackTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "CALLBACK RECORDS",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
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
            label = { Text("Mobile Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = callbackDate,
            onValueChange = { callbackDate = it },
            label = { Text("Callback Date") },
            placeholder = { Text("DD/MM/YYYY") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = callbackTime,
            onValueChange = { callbackTime = it },
            label = { Text("Callback Time") },
            placeholder = { Text("HH:MM") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                // Callback saving will be connected next.
            },
            enabled = customerName.isNotBlank() &&
                    mobileNumber.length == 10 &&
                    callbackDate.isNotBlank() &&
                    callbackTime.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SAVE CALLBACK")
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
