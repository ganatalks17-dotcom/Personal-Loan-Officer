package com.personal.loanofficer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.personal.loanofficer.data.CustomerRepository
import com.personal.loanofficer.data.database.CustomerEntity
import kotlinx.coroutines.launch

@Composable
fun CustomerListScreen(
    repository: CustomerRepository,
    onCustomerSelected: (CustomerEntity) -> Unit,
    onBack: () -> Unit
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val customers by repository
        .searchCustomers(searchText)
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "SAVED CUSTOMERS",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            label = {
                Text("Search name or mobile")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        if (customers.isEmpty()) {

            Text(
                text = "No customers found.",
                modifier = Modifier.padding(top = 20.dp)
            )

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = customers,
                    key = { it.id }
                ) { customer ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = customer.customerName,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = customer.mobileNumber
                            )

                            Spacer(Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        onCustomerSelected(customer)
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("SELECT")
                                }

                                OutlinedButton(
                                    onClick = {
                                        scope.launch {
                                            repository.deleteCustomer(customer)
                                        }
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("DELETE")
                                }
                            }
                        }
                    }
                }
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
