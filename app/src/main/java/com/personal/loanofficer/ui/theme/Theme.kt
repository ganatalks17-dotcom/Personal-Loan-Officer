package com.personal.loanofficer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LoanColorScheme = lightColorScheme(
    primary = LoanBlue,
    onPrimary = LoanWhite,

    secondary = LoanDarkBlue,
    onSecondary = LoanWhite,

    background = LoanWhite,
    onBackground = LoanBlack,

    surface = LoanWhite,
    onSurface = LoanBlack,

    error = LoanRed,
    onError = LoanWhite
)

@Composable
fun PersonalLoanOfficerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LoanColorScheme,
        content = content
    )
}