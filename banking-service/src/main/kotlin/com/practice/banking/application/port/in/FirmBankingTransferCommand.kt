package com.practice.banking.application.port.`in`

data class FirmBankingTransferCommand(
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int,
)