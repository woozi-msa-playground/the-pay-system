package com.practive.payment.application.port.`in`

fun interface RequestPaymentUsecase {
    fun requestPayment(command: RequestPaymentCommand)
}