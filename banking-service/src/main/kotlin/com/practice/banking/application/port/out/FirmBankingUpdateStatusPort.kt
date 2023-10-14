package com.practice.banking.application.port.out

import com.practice.banking.domain.FirmBanking
import com.practice.banking.domain.vo.FirmBankingId
import com.practice.banking.domain.vo.FirmBankingStatus

fun interface FirmBankingUpdateStatusPort {
    fun updateFirmBankingStatus(firmBanking: FirmBanking): FirmBanking =
        this.updateFirmBankingStatus(
            FirmBankingId(firmBanking.firmBankingId),
            FirmBankingStatus(firmBanking.firmBankingStatus)
        )

    fun updateFirmBankingStatus(
        firmBankingId: FirmBankingId,
        firmBankingStatus: FirmBankingStatus,
    ): FirmBanking
}