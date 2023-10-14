package com.practice.banking.application.port.`in`

import com.practice.banking.domain.FirmBanking

fun interface FirmBankingTransferUseCase {
    fun transferFirmBanking(command: FirmBankingTransferCommand): FirmBanking
}