package com.practice.money.application.port.out

data class RegisteredBankAccountAggregate (
    val aggregateIdentifier: String,
    val registeredBankAccountId: String,
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
)