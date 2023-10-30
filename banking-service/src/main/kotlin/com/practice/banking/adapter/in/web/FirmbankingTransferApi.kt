package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.FirmBankingTransferEdaUseCase
import com.practice.banking.application.port.`in`.FirmBankingTransferUseCase
import com.practice.banking.domain.FirmBanking
import com.practice.common.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FirmbankingTransferApi(
    private val firmBankingTransferUseCase: FirmBankingTransferUseCase,
    private val firmBankingTransferEdaUseCase: FirmBankingTransferEdaUseCase,
) {
    @PostMapping("/bank/firmbanking/transfer")
    fun transferFirmBanking(@RequestBody request: FirmBankingTransferApiRequest): ResponseEntity<FirmBanking> {
        val transferFirmBanking = firmBankingTransferUseCase.transferFirmBanking(request.toCommand())
        return ResponseEntity.ok().body(transferFirmBanking)
    }

    @PostMapping("/bank/firmbanking/transfer-eda")
    fun transferFirmBankingEda(@RequestBody request: FirmBankingTransferApiRequest): ResponseEntity<Nothing> {
        firmBankingTransferEdaUseCase.transferFirmBankingEda(request.toCommand())
        return ResponseEntity.ok().build()
    }
}
