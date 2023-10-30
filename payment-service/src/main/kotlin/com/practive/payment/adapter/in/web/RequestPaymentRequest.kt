package com.practive.payment.adapter.`in`.web

import com.practive.payment.application.port.`in`.RequestPaymentCommand
import java.time.ZonedDateTime

data class RequestPaymentRequest(
    val membershipId: String,
    val requestPrice: String,
    val franchiseId: String,
    val franchiseFeeRate: String,
    val paymentStatus: Int,
    val approvedAt: ZonedDateTime
) {
    fun toCommand(): RequestPaymentCommand =
        RequestPaymentCommand(membershipId, requestPrice, franchiseId, franchiseFeeRate, paymentStatus, approvedAt)
}
