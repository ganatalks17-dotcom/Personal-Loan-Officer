package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment

@Composable
fun LoginScreen(
    onLoginSuccess: (String, String) -> Unit
) {

    var executiveName by remember {
        mutableStateOf("")
    }

    var mobileNumber by remember {
        mutableStateOf("")
    }

    val isValid =
        executiveName.trim().isNotEmpty() &&
        mobileNumber.length == 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "PERSONAL LOAN OFFICER",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Executive Login",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        OutlinedTextField(
            value = executiveName,
            onValueChange = {
                executiveName = it
            },
            label = {
                Text("Executive Name")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = mobileNumber,
            onValueChange = {
                if (it.all { character -> character.isDigit() } &&
                    it.length <= 10
                ) {
                    mobileNumber = it
                }
            },
            label = {
                Text("Mobile Number")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            singleLine = true
        )

        Button(
            onClick = {
                onLoginSuccess(
                    executiveName.trim(),
                    mobileNumber
                )
            },
            enabled = isValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("CONTINUE")
        }
    }
}