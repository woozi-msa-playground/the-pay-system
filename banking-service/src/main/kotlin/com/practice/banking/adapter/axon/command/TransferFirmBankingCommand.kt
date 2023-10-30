package com.practice.banking.adapter.axon.command

data class TransferFirmBankingCommand(
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int,
)