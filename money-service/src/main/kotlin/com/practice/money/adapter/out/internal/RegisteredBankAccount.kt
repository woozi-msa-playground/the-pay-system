package com.practice.money.adapter.out.internal

class RegisteredBankAccount(
    val bankAccountId: String,
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedIsValid: Boolean,
    val bankAccountAggregateIdentifier: String,
)