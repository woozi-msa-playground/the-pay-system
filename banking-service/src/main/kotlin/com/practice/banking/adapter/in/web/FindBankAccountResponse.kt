package com.practice.banking.adapter.`in`.web

import com.practice.banking.domain.BankAccount

data class FindBankAccountResponse(
    val bankAccountId: String,
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedIsValid: Boolean,
    val bankAccountAggregateIdentifier: String,
) {
    constructor(bankAccount: BankAccount) : this(
        bankAccount.bankAccountId,
        bankAccount.membershipId,
        bankAccount.bankName,
        bankAccount.bankAccountNumber,
        bankAccount.linkedIsValid,
        bankAccount.bankAccountAggregateIdentifier
    )
}
