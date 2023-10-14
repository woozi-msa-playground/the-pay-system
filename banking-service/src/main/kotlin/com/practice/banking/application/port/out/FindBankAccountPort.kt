package com.practice.banking.application.port.out

import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountId

fun interface FindBankAccountPort {

    fun findBankAccount(bankAccountId: Long): BankAccount =
        findBankAccount(BankAccountId(bankAccountId.toString()))

    fun findBankAccount(bankAccountId: BankAccountId): BankAccount
}