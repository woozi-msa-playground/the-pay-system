package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.BankAccountFindUseCase
import com.pratice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindBankAccountApi(
    private val bankAccountFindUseCase: BankAccountFindUseCase
) {

    @GetMapping("/bank/{bankAccountId}")
    fun findMembership(@PathVariable bankAccountId: Long): ResponseEntity<FindBankAccountResponse> =
        ResponseEntity.ok().body(
            FindBankAccountResponse(bankAccountFindUseCase.findBankAccount(bankAccountId))
        )
}