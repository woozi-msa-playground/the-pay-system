package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.BankAccountRegisterUseCase
import com.pratice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterBankAccountApi(
    private val bankAccountRegisterUseCase: BankAccountRegisterUseCase
) {

    @PostMapping("/bank/register")
    fun registerMembership(@RequestBody registerBankAccountRequest: RegisterBankAccountRequest): ResponseEntity<RegisterBankAccountResponse> =
        ResponseEntity.ok().body(
            RegisterBankAccountResponse(bankAccountRegisterUseCase.registerBankAccount(registerBankAccountRequest.toCommand()))
        )
}