package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    executiveName: String,
    onCalculatorClick: () -> Unit,
    onCallbacksClick: () -> Unit,
    onSettingsClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "PERSONAL LOAN OFFICER",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Welcome, $executiveName",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Normal EMI Calculator",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Calculate monthly EMI and loan details.",
                    modifier = Modifier.padding(top = 8.dp)
                )

                Button(
                    onClick = onCalculatorClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("OPEN CALCULATOR")
                }
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(Modifier.fillMaxWidth()) {

    Column(
        Modifier.padding(20.dp)
    ) {

        Text(
            "Customer Request Search",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            "Search customer information before calculating.",
            Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = {
                // Customer search will be connected from MainActivity.
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("SEARCH CUSTOMER")
        }
    }
}

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Callback Records",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "View and manage customer callbacks.",
                    modifier = Modifier.padding(top = 8.dp)
                )

                Button(
                    onClick = onCallbacksClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("OPEN CALLBACKS")
                }
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { }
            ) {
                Text("HOME")
            }

            Button(
                onClick = onCalculatorClick
            ) {
                Text("CALCULATOR")
            }

            Button(
                onClick = onCallbacksClick
            ) {
                Text("CALLBACKS")
            }

            Button(
                onClick = onSettingsClick
            ) {
                Text("SETTINGS")
            }
        }
    }
}
