package com.personal.loanofficer.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class EmiCalculatorTest {

    @Test
    fun referenceEmiCalculation() {

        val result = calculateEmi(
            principal = 880000.0,
            annualRate = 19.25,
            tenureMonths = 48
        )

        assertEquals(
            26428.32,
            result.emi,
            0.10
        )
    }
}
