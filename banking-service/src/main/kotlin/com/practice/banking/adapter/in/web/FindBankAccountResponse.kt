package com.practice.banking.adapter.`in`.web

import com.practice.banking.domain.BankAccount

data class FindBankAccountResponse(
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedIsValid: Boolean,
) {
    constructor(bankAccount: BankAccount) : this(
        bankAccount.membershipId,
        bankAccount.bankName,
        bankAccount.bankAccountNumber,
        bankAccount.linkedIsValid,
    )
}
