package com.practice.banking.application.port.`in`

fun interface FirmBankingTransferEdaUseCase {
    fun transferFirmBankingEda(command: FirmBankingTransferCommand)
}