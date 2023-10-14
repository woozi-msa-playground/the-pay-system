package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.FirmBankingTransferCommand

data class FirmBankingTransferApiRequest(
    private val fromBankName: String,
    private val fromBankAccountNumber: String,
    private val toBankName: String,
    private val toBankAccountNumber: String,
    private val moneyAmount: Int,
    private val firmBankingStatus: Int,
) {
    fun toCommand(): FirmBankingTransferCommand =
        FirmBankingTransferCommand(
            fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber, moneyAmount
        )
}
