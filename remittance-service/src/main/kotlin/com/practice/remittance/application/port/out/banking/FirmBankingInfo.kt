package com.practice.remittance.application.port.out.banking

data class FirmBankingInfo(
    val firmBankingId: String,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int,
    val firmBankingStatus: Int,
)
