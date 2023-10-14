package com.practice.banking.application

import com.practice.banking.adapter.out.external.firmbank.ExternalFirmBankingRequest
import com.practice.banking.application.port.`in`.FirmBankingTransferCommand
import com.practice.banking.application.port.`in`.FirmBankingTransferUseCase
import com.practice.banking.application.port.out.ExternalFirmBankingPort
import com.practice.banking.application.port.out.FirmBankingRegisterPort
import com.practice.banking.application.port.out.FirmBankingUpdateStatusPort
import com.practice.banking.domain.FirmBanking
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName
import com.practice.common.UseCase

@UseCase
class FirmBankingTransferUseCaseImpl(
    private val firmBankingRegisterPort: FirmBankingRegisterPort,
    private val firmBankingUpdateStatusPort: FirmBankingUpdateStatusPort,
    private val externalFirmBankingPort: ExternalFirmBankingPort

) : FirmBankingTransferUseCase {

    override fun transferFirmBanking(command: FirmBankingTransferCommand): FirmBanking {
        val firmBanking = firmBankingRegisterPort.createFirmBanking(
            FromBankName(command.fromBankName),
            FromBankAccountNumber(command.fromBankAccountNumber),
            ToBankName(command.toBankName),
            ToBankAccountNumber(command.toBankAccountNumber),
            MoneyAmount(command.moneyAmount),
            FirmBankingStatus(0)
        )

        val firmBankingResult = externalFirmBankingPort.transferExternalFirmBanking(
            ExternalFirmBankingRequest(
                command.fromBankName,
                command.fromBankAccountNumber,
                command.toBankName,
                command.toBankAccountNumber
            )
        )
        if (firmBankingResult.responseCode == 0) {
            return firmBankingUpdateStatusPort.updateFirmBankingStatus(firmBanking.updateStatus(1))
        }
        return firmBankingUpdateStatusPort.updateFirmBankingStatus(firmBanking.updateStatus(2))
    }
}
