package com.practice.banking.adapter.axon.event

data class CreateRegisteredBankAccountAggregateEvent(
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String
)