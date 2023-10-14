package com.practice.banking.adapter.`in`.web

import com.practice.banking.domain.BankAccount

data class RegisterBankAccountResponse(
    val bankAccountId: String,
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedIsValid: Boolean,
) {
    constructor(bankAccount: BankAccount) : this(
        bankAccount.bankAccountId,
        bankAccount.membershipId,
        bankAccount.bankName,
        bankAccount.bankAccountNumber,
        bankAccount.linkedIsValid,
    )
}
