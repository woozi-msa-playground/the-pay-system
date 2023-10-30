package com.practice.banking.adapter.axon.aggregate

import com.practice.banking.adapter.axon.command.TransferFirmBankingCommand
import com.practice.banking.adapter.axon.event.TransferFirmBankingEvent
import com.practice.banking.adapter.out.external.firmbank.ExternalFirmBankingRequest
import com.practice.banking.application.port.out.ExternalFirmBankingPort
import com.practice.banking.application.port.out.RequestFirmBankingRequestPort
import com.practice.banking.domain.vo.FirmBankingStatus
import com.practice.banking.domain.vo.FirmbankingAggregateIdentifier
import com.practice.banking.domain.vo.FromBankAccountNumber
import com.practice.banking.domain.vo.FromBankName
import com.practice.banking.domain.vo.MoneyAmount
import com.practice.banking.domain.vo.ToBankAccountNumber
import com.practice.banking.domain.vo.ToBankName
import com.practice.common.command.RequestFirmBankingCommand
import com.practice.common.command.RollBackFirmBankingRequestCommand
import com.practice.common.event.RequestFirmBankingResultFinishedEvent
import com.practice.common.event.RollbackFirmBankingResultFinishedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID

@Aggregate
data class FirmbankingRequestAggregate(

    @AggregateIdentifier
    var id: String? = null,
    var fromBankName: String? = null,
    var fromBankAccountNumber: String? = null,
    var toBankName: String? = null,
    var toBankAccountNumber: String? = null,
    var moneyAmount: Int? = null,
    var firmBankingStatus: String? = null

) {

    @CommandHandler
    constructor(transferFirmBankingCommand: TransferFirmBankingCommand) : this() {
        AggregateLifecycle.apply(
            TransferFirmBankingEvent(
                transferFirmBankingCommand.fromBankName,
                transferFirmBankingCommand.fromBankAccountNumber,
                transferFirmBankingCommand.toBankName,
                transferFirmBankingCommand.toBankAccountNumber,
                transferFirmBankingCommand.moneyAmount,
            )
        )
    }

    @EventSourcingHandler
    fun on(transferFirmBankingEvent: TransferFirmBankingEvent) {
        this.id = UUID.randomUUID().toString()
        this.fromBankName = transferFirmBankingEvent.fromBankName
        this.fromBankAccountNumber = transferFirmBankingEvent.fromBankAccountNumber
        this.toBankName = transferFirmBankingEvent.toBankName
        this.toBankAccountNumber = transferFirmBankingEvent.toBankAccountNumber
        this.moneyAmount = transferFirmBankingEvent.moneyAmount
    }

    @CommandHandler
    constructor(command: RequestFirmBankingCommand, requestFirmBankingRequestPort: RequestFirmBankingRequestPort, externalFirmBankingPort: ExternalFirmBankingPort) : this() {
        id = command.aggregateIdentifier
        requestFirmBankingRequestPort.createFirmBankingRequest(
            fromBankAccountNumber = FromBankAccountNumber(command.fromBankAccountNumber),
            fromBankName = FromBankName(command.fromBankName),
            toBankName = ToBankName(command.toBankName),
            toBankAccountNumber = ToBankAccountNumber(command.toBankAccountNumber),
            moneyAmount = MoneyAmount(command.amount),
            firmBankingStatus = FirmBankingStatus(0),
            firmbankingAggregateIdentifier = FirmbankingAggregateIdentifier(id!!),
        )

        val firmBankingResult = externalFirmBankingPort.transferExternalFirmBanking(
            ExternalFirmBankingRequest(
                fromBankName = command.fromBankName,
                fromBankAccountNumber = command.fromBankAccountNumber,
                toBankName = command.toBankName,
                toBankAccountNumber = command.toBankAccountNumber,
                moneyAmount = command.amount,
            )
        )
        val requestFirmBankingResultFinishedEvent = RequestFirmBankingResultFinishedEvent(
            requestFirmBankingId = command.requestFirmBankingId,
            rechargeRequestId = command.rechargeRequestId,
            membershipId = command.membershipId,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            checkRegisteredBankAccountId = command.checkRegisteredBankAccountId,
            status = firmBankingResult.responseCode,
            amount = command.amount
        )
        AggregateLifecycle.apply(requestFirmBankingResultFinishedEvent)
    }

    @CommandHandler
    constructor(command: RollBackFirmBankingRequestCommand, requestFirmBankingRequestPort: RequestFirmBankingRequestPort, externalFirmBankingPort: ExternalFirmBankingPort) : this() {
        id = command.aggregateIdentifier
        requestFirmBankingRequestPort.createFirmBankingRequest(
            fromBankAccountNumber = FromBankAccountNumber(command.fromBankAccountNumber),
            fromBankName = FromBankName(command.fromBankName),
            toBankName = ToBankName(command.toBankName),
            toBankAccountNumber = ToBankAccountNumber(command.toBankAccountNumber),
            moneyAmount = MoneyAmount(command.amount),
            firmBankingStatus = FirmBankingStatus(0),
            firmbankingAggregateIdentifier = FirmbankingAggregateIdentifier(id!!),
        )

        val firmBankingResult = externalFirmBankingPort.transferExternalFirmBanking(
            ExternalFirmBankingRequest(
                fromBankName = command.fromBankName,
                fromBankAccountNumber = command.fromBankAccountNumber,
                toBankName = command.toBankName,
                toBankAccountNumber = command.toBankAccountNumber,
                moneyAmount = command.amount,
            )
        )

        val rollBackFirmBankingResultFinishedEvent = RollbackFirmBankingResultFinishedEvent(
            rollbackFirmBankingId = command.rollbackFirmBankingId,
            rechargeRequestId = command.rechargeRequestId,
            membershipId = command.membershipId,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            checkRegisteredBankAccountId = command.checkRegisteredBankAccountId,
            status = firmBankingResult.responseCode,
            amount = command.amount
        )
        AggregateLifecycle.apply(rollBackFirmBankingResultFinishedEvent)
    }

}