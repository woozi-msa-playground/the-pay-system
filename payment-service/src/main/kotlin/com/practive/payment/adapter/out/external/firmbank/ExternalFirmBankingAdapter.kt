package com.practive.payment.adapter.out.external.firmbank

import com.practice.banking.application.port.out.ExternalFirmBankingPort
import com.practice.common.ExternalSystemAdapter

@ExternalSystemAdapter
class ExternalFirmBankingAdapter: ExternalFirmBankingPort {

    override fun transferExternalFirmBanking(externalFirmBankingRequest :ExternalFirmBankingRequest): ExternalFirmBankingResult =
        ExternalFirmBankingResult(200)
}