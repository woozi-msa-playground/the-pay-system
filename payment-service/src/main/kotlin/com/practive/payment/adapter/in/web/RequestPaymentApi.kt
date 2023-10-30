package com.practive.payment.adapter.`in`.web

import com.practice.common.WebAdapter
import com.practive.payment.application.port.`in`.RequestPaymentUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestPaymentApi(
    private val requestPaymentUsecase: RequestPaymentUsecase
) {

    @PostMapping("/payment/request")
    fun requestPayment(@RequestBody requestPaymentRequest: RequestPaymentRequest): ResponseEntity<Any> =
        ResponseEntity.ok().body(requestPaymentUsecase.requestPayment(requestPaymentRequest.toCommand()))
}