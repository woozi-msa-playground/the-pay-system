package com.practice.banking.adapter.axon.event

data class TransferFirmBankingEvent(
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int,
)