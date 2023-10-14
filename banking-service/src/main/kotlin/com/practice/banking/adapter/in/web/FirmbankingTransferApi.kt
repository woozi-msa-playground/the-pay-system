package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.FirmBankingTransferUseCase
import com.pratice.common.WebAdapter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FirmbankingTransferApi(
    private val firmBankingTransferUseCase: FirmBankingTransferUseCase
) {
    @PostMapping("/bank/firmbanking/transfer")
    fun transferFirmBanking(@RequestBody request: FirmBankingTransferApiRequest) {
        firmBankingTransferUseCase.transferFirmBanking(request.toCommand())
    }
}