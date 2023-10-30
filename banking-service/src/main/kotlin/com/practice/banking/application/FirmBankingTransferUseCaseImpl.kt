package com.practice.banking.application

import com.practice.banking.adapter.axon.command.TransferFirmBankingCommand
import com.practice.banking.adapter.out.external.firmbank.ExternalFirmBankingRequest
import com.practice.banking.application.port.`in`.FirmBankingTransferCommand
import com.practice.banking.application.port.`in`.FirmBankingTransferEdaUseCase
import com.practice.banking.application.port.`in`.FirmBankingTransferUseCase
import com.practice.banking.application.port.out.ExternalFirmBankingPort
import com.practice.banking.application.port.out.RequestFirmBankingRequestPort
import com.practice.banking.application.port.out.FirmBankingUpdateStatusPort
import com.practice.banking.domain.FirmBanking
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FirmbankingAggregateIdentifier
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName
import com.practice.common.UseCase
import org.axonframework.commandhandling.gateway.CommandGateway
import java.util.UUID

@UseCase
class FirmBankingTransferUseCaseImpl(
    private val commandGateway: CommandGateway,
    private val requestFirmBankingRequestPort: RequestFirmBankingRequestPort,
    private val firmBankingUpdateStatusPort: FirmBankingUpdateStatusPort,
    private val externalFirmBankingPort: ExternalFirmBankingPort
) : FirmBankingTransferUseCase, FirmBankingTransferEdaUseCase {

    override fun transferFirmBanking(command: FirmBankingTransferCommand): FirmBanking {
        val firmBanking = requestFirmBankingRequestPort.createFirmBankingRequest(
            FromBankName(command.fromBankName),
            FromBankAccountNumber(command.fromBankAccountNumber),
            ToBankName(command.toBankName),
            ToBankAccountNumber(command.toBankAccountNumber),
            MoneyAmount(command.moneyAmount),
            FirmBankingStatus(0),
            FirmbankingAggregateIdentifier(UUID.randomUUID().toString())
        )

        val firmBankingResult = externalFirmBankingPort.transferExternalFirmBanking(
            ExternalFirmBankingRequest(
                command.fromBankName,
                command.fromBankAccountNumber,
                command.toBankName,
                command.toBankAccountNumber,
                command.moneyAmount
            )
        )
        if (firmBankingResult.responseCode == 0) {
            return firmBankingUpdateStatusPort.updateFirmBankingStatus(firmBanking.updateStatus(1))
        }
        return firmBankingUpdateStatusPort.updateFirmBankingStatus(firmBanking.updateStatus(2))
    }

    override fun transferFirmBankingEda(command: FirmBankingTransferCommand) {
        val transferFirmBankingCommand = TransferFirmBankingCommand(
            command.fromBankName,
            command.fromBankAccountNumber,
            command.toBankName,
            command.toBankAccountNumber,
            command.moneyAmount,
        )
        commandGateway.send<String>(transferFirmBankingCommand).whenComplete { result, throwable ->
            if(throwable != null) {
                throw RuntimeException()
            }
            val firmBanking = requestFirmBankingRequestPort.createFirmBankingRequest(
                FromBankName(command.fromBankName),
                FromBankAccountNumber(command.fromBankAccountNumber),
                ToBankName(command.toBankName),
                ToBankAccountNumber(command.toBankAccountNumber),
                MoneyAmount(command.moneyAmount),
                FirmBankingStatus(0),
                FirmbankingAggregateIdentifier(result)
            )
            val firmBankingResult = externalFirmBankingPort.transferExternalFirmBanking(
                ExternalFirmBankingRequest(
                    command.fromBankName,
                    command.fromBankAccountNumber,
                    command.toBankName,
                    command.toBankAccountNumber,
                    command.moneyAmount
                )
            )
            if (firmBankingResult.responseCode == 200) {
                firmBankingUpdateStatusPort.updateFirmBankingStatus(firmBanking.updateStatus(1))
            }
            firmBankingUpdateStatusPort.updateFirmBankingStatus(firmBanking.updateStatus(2))
        }
    }
}
