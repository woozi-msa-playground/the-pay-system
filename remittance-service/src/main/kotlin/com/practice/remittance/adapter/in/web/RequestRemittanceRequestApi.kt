package com.practice.remittance.adapter.`in`.web

import com.practice.remittance.application.RequestRemittanceUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RequestRemittanceRequestApi(
    private val requestRemittanceUseCase: RequestRemittanceUseCase,
) {

    @PostMapping("/remittance")
    fun test(requestRemittanceRequest: RequestRemittanceRequest): ResponseEntity<RequestRemittanceResponse> {
        val requestRemittanceResponse =
            RequestRemittanceResponse(requestRemittanceUseCase.requestRemittance(requestRemittanceRequest.toCommand()))
        return ResponseEntity.ok().body(requestRemittanceResponse)
    }
}