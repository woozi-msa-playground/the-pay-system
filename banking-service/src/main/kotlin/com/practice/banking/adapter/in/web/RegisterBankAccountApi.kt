package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.BankAccountRegisterEdaUseCase
import com.practice.banking.application.port.`in`.BankAccountRegisterUseCase
import com.practice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterBankAccountApi(
    private val bankAccountRegisterUseCase: BankAccountRegisterUseCase,
    private val bankAccountRegisterEdaUseCase: BankAccountRegisterEdaUseCase
) {

    @PostMapping("/bank/register")
    fun registerBankAccount(@RequestBody registerBankAccountRequest: RegisterBankAccountRequest): ResponseEntity<RegisterBankAccountResponse> =
        ResponseEntity.ok().body(
            RegisterBankAccountResponse(bankAccountRegisterUseCase.registerBankAccount(registerBankAccountRequest.toCommand()))
        )

    @PostMapping("/bank/register/eda")
    fun registerBankAccountEda(@RequestBody registerBankAccountRequest: RegisterBankAccountRequest): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            bankAccountRegisterEdaUseCase.registerBankAccountEda(registerBankAccountRequest.toCommand())
        )
}