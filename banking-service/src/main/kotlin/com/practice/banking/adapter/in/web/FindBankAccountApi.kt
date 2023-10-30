package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.BankAccountFindEdaUseCase
import com.practice.banking.application.port.`in`.BankAccountFindUseCase
import com.practice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindBankAccountApi(
    private val bankAccountFindUseCase: BankAccountFindUseCase,
    private val bankAccountFindEdaUseCase: BankAccountFindEdaUseCase,
) {

    @GetMapping("/bank/{bankAccountId}")
    fun findMembership(@PathVariable bankAccountId: Long): ResponseEntity<FindBankAccountResponse> =
        ResponseEntity.ok().body(
            FindBankAccountResponse(bankAccountFindUseCase.findBankAccount(bankAccountId))
        )

    @GetMapping("/banking/account/{membershipId}")
    fun findMembershipEda(@PathVariable membershipId: Long): ResponseEntity<FindBankAccountResponse> =
        ResponseEntity.ok().body(
            FindBankAccountResponse(bankAccountFindEdaUseCase.findBankAccountEda(membershipId))
        )
}