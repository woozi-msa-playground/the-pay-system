package com.practice.money.adapter.axon.event

data class RechargingRequestCreatedEvent(
    val rechargingRequestId: String,
    val membershipId: String,
    val amount: Int,
    val registeredBankAccountAggregateIdentifier: String,
    val bankName: String,
    val bankAccountNumber: String
)