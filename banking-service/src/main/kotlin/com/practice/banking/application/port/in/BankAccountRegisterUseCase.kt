package com.practice.banking.application.port.`in`

import com.practice.banking.domain.BankAccount

fun interface BankAccountRegisterUseCase {
    fun registerBankAccount(command: BankAccountRegisterCommand): BankAccount
}