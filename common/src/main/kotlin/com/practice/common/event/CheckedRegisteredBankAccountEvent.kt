package com.practice.common.event

data class CheckedRegisteredBankAccountEvent(
    val rechargeRequestId: String,
    val checkRegisteredBankAccountId: String,
    val membershipId: String,
    val isChecked: Boolean,
    val amount: Int,
    val firmbankingAggregateIdentifier: String,
    val bankName: String,
    val bankAccountNumber: String,
)