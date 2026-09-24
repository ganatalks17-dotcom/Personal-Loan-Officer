package com.personal.loanofficer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.personal.loanofficer.ui.CalculatorScreen
import com.personal.loanofficer.ui.HomeScreen
import com.personal.loanofficer.ui.LoginScreen
import com.personal.loanofficer.ui.theme.PersonalLoanOfficerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PersonalLoanOfficerTheme {

                var loggedIn by remember {
                    mutableStateOf(false)
                }

                var executiveName by remember {
                    mutableStateOf("")
                }

                var currentScreen by remember {
                    mutableStateOf("home")
                }

                if (!loggedIn) {

                    LoginScreen(
                        onLoginSuccess = { name, mobile ->
                            executiveName = name
                            loggedIn = true
                        }
                    )

                } else {

                    when (currentScreen) {

                        "calculator" -> {

                            CalculatorScreen(
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

                                onCallbacksClick = {
                                    // Callback screen will be added later.
                                },

                                onSettingsClick = {
                                    // Settings screen will be added later.
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}



