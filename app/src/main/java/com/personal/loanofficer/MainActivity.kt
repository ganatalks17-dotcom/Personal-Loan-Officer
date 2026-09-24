package com.personal.loanofficer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.personal.loanofficer.ui.LoginScreen
import com.personal.loanofficer.ui.theme.PersonalLoanOfficerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PersonalLoanOfficerTheme {

                LoginScreen(
                    onLoginSuccess = { name, mobile ->

                        // Home screen will be connected here later.

                    }
                )
            }
        }
    }
}