package com.practice.banking.application.port.out

import com.practice.banking.adapter.out.external.firmbank.ExternalFirmBankingRequest
import com.practice.banking.adapter.out.external.firmbank.ExternalFirmBankingResult

fun interface ExternalFirmBankingPort {
    fun transferExternalFirmBanking(externalFirmBankingRequest : ExternalFirmBankingRequest): ExternalFirmBankingResult
}