package com.practice.remittance.adapter.`in`.web

import com.practice.remittance.application.FindRemittanceUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FindRemittanceRequestApi(
    private val findRemittanceUseCase: FindRemittanceUseCase
) {

    @GetMapping("/remittance/{membershipId}")
    fun findRemittanceByMembershipId(@PathVariable membershipId: String): ResponseEntity<FindRemittanceResponse> {
        val remittanceHistories = findRemittanceUseCase.findRemittanceByMembershipId(FindRemittanceCommand(membershipId))
        val findRemittanceResponse = FindRemittanceResponse(remittanceHistories.map(::RemittanceHistoryResponse))
        return ResponseEntity.ok().body(findRemittanceResponse)
    }
}