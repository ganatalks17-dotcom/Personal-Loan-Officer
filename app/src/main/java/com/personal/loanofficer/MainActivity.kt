package com.personal.loanofficer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.personal.loanofficer.data.CustomerRepository
import com.personal.loanofficer.data.database.AppDatabase
import com.personal.loanofficer.ui.CalculatorScreen
import com.personal.loanofficer.ui.CustomerListScreen
import com.personal.loanofficer.ui.CustomerSearchScreen
import com.personal.loanofficer.ui.HomeScreen
import com.personal.loanofficer.ui.LoginScreen
import com.personal.loanofficer.ui.theme.PersonalLoanOfficerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences(
            "loan_officer_preferences",
            MODE_PRIVATE
        )

        val savedName = preferences.getString(
            "executive_name",
            ""
        ) ?: ""

        val savedMobile = preferences.getString(
            "executive_mobile",
            ""
        ) ?: ""

        val database = AppDatabase.getDatabase(this)

        val customerRepository =
            CustomerRepository(database.customerDao())

        setContent {

            PersonalLoanOfficerTheme {

                val scope = rememberCoroutineScope()

                var loggedIn by remember {
                    mutableStateOf(
                        savedName.isNotEmpty() &&
                        savedMobile.length == 10
                    )
                }

                var executiveName by remember {
                    mutableStateOf(savedName)
                }

                var currentScreen by remember {
                    mutableStateOf("home")
                }

                var selectedCustomerName by remember {
                    mutableStateOf("")
                }

                var selectedCustomerMobile by remember {
                    mutableStateOf("")
                }

                if (!loggedIn) {

                    LoginScreen(
                        onLoginSuccess = { name, mobile ->

                            preferences.edit()
                                .putString(
                                    "executive_name",
                                    name
                                )
                                .putString(
                                    "executive_mobile",
                                    mobile
                                )
                                .apply()

                            executiveName = name
                            loggedIn = true
                        }
                    )

                } else {

                    when (currentScreen) {

                        "calculator" -> {

                            CalculatorScreen(
                                customerName = selectedCustomerName,
                                customerMobile = selectedCustomerMobile,
                                onBack = {
                                    currentScreen = "home"
                                }
                            )
                        }

                        "customerSearch" -> {

                            CustomerSearchScreen(

                                onCustomerSelected = { name, mobile ->

                                    scope.launch {

                                        customerRepository.saveCustomer(
                                            customerName = name,
                                            mobileNumber = mobile
                                        )
                                    }

                                    selectedCustomerName = name
                                    selectedCustomerMobile = mobile

                                    currentScreen = "calculator"
                                },

                                onBack = {
                                    currentScreen = "home"
                                }
                            )
                        }

                        "customerList" -> {

                            CustomerListScreen(

                                repository = customerRepository,

                                onCustomerSelected = { customer ->

                                    selectedCustomerName =
                                        customer.customerName

                                    selectedCustomerMobile =
                                        customer.mobileNumber

                                    currentScreen = "calculator"
                                },

                                onBack = {
                                    currentScreen = "home"
                                }
                            )
                        }

                        else -> {

                            HomeScreen(

                                executiveName = executiveName,

                                onCalculatorClick = {
                                    currentScreen = "calculator"
                                },

                                onCustomerSearchClick = {
                                    currentScreen = "customerSearch"
                                },

                                onCustomerListClick = {
                                    currentScreen = "customerList"
                                },

                                onCallbacksClick = {
                                },

                                onSettingsClick = {
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
