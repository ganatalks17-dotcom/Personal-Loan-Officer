package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    executiveName: String,
    onCalculatorClick: () -> Unit,
    onCustomerSearchClick: () -> Unit,
    onCustomerListClick: () -> Unit,
    onCallbacksClick: () -> Unit,
    onSettingsClick: () -> Unit
)
 {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "PERSONAL LOAN OFFICER",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Welcome, $executiveName",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(24.dp))

        Card(
            Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier.padding(20.dp)
            ) {

                Text(
                    "Normal EMI Calculator",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    "Calculate monthly EMI and loan details.",
                    Modifier.padding(top = 8.dp)
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

        Spacer(Modifier.height(16.dp))

        Card(
            Modifier.fillMaxWidth()
        ) {
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
                    onClick = onCustomerSearchClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("SEARCH CUSTOMER")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier.padding(20.dp)
            ) {

                Text(
                    "Callback Records",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    "View and manage customer callbacks.",
                    Modifier.padding(top = 8.dp)
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

        Spacer(Modifier.weight(1f))

        Row(
            Modifier.fillMaxWidth(),
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
