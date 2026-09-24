package com.personal.loanofficer.rate

data class RateBand(
    val minAmount: Int,
    val maxAmount: Int,
    val salariedNew: Double?,
    val salariedPltb: Double?,
    val selfEmployedNew: Double?,
    val selfEmployedPltb: Double?
)

val rateTable = listOf(

    RateBand(40000, 149999, 29.99, 29.99, 29.99, 29.99),

    RateBand(150000, 229999, 29.99, 29.99, 29.99, 29.99),

    RateBand(230000, 299999, 29.99, 29.99, 29.99, 29.99),

    RateBand(300000, 334999, 27.75, 26.50, 28.00, 26.99),

    RateBand(335000, 399999, 24.75, 23.25, 25.25, 23.75),

    RateBand(400000, 449999, 23.75, 22.25, 24.25, 22.75),

    RateBand(450000, 499999, 22.25, 21.75, 23.25, 22.50),

    RateBand(500000, 549999, 21.75, 20.75, 22.25, 21.25),

    RateBand(550000, 574999, 21.25, 20.75, 21.75, 21.25),

    RateBand(575000, 624999, 20.75, 20.25, 21.25, 20.75),

    RateBand(625000, 649999, 20.00, 19.75, 20.25, null),

    RateBand(650000, 699999, 19.50, 19.50, 20.00, null),

    RateBand(700000, 749999, 19.25, 19.25, 19.75, null),

    RateBand(750000, 799999, 19.25, 19.25, 19.75, null),

    RateBand(800000, 1150000, 19.25, 19.25, 19.75, null)
)
