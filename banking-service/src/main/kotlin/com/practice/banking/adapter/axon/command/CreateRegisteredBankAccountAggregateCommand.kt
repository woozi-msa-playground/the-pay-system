package com.practice.banking.adapter.axon.command

data class CreateRegisteredBankAccountAggregateCommand(
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String
) {
}