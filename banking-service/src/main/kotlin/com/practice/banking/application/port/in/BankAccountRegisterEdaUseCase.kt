package com.practice.banking.application.port.`in`

fun interface BankAccountRegisterEdaUseCase {
    fun registerBankAccountEda(command: BankAccountRegisterCommand): Boolean
}