package com.practive.payment.application.port.`in`

import java.time.ZonedDateTime

data class RequestPaymentCommand(
    val membershipId: String,
    val requestPrice: String,
    val franchiseId: String,
    val franchiseFeeRate: String,
    val paymentStatus: Int,
    val approvedAt: ZonedDateTime
)
