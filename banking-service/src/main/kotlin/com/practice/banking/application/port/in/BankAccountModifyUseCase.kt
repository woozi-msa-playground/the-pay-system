package com.practice.banking.application.port.`in`

import com.practice.banking.domain.BankAccount

fun interface BankAccountModifyUseCase {
    fun modifyBankAccount(command: BankAccountModifyCommand): BankAccount
}
