package com.practice.banking.application.port.`in`

import com.practice.banking.domain.BankAccount

fun interface BankAccountFindUseCase {
    fun findBankAccount(bankAccountId: Long): BankAccount
}