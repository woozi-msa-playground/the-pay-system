package com.practice.banking.adapter.axon.aggregate

import com.practice.banking.adapter.axon.command.CreateRegisteredBankAccountAggregateCommand
import com.practice.banking.adapter.axon.event.CreateRegisteredBankAccountAggregateEvent
import com.practice.banking.adapter.out.external.bank.RequestSearchBankAccount
import com.practice.banking.application.port.out.ExternalBankAccountPort
import com.practice.common.command.CheckRegisteredBankAccountCommand
import com.practice.common.event.CheckedRegisteredBankAccountEvent
import jakarta.validation.constraints.NotNull
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID

@Aggregate
class RegisteredBankAccountAggregate(
    @AggregateIdentifier private var id: String? = null,
    private var membershipId: String? = null,
    private var bankName: String? = null,
    private var bankAccountNumber: String? = null
) {

    @CommandHandler
    constructor(command: CreateRegisteredBankAccountAggregateCommand) : this() {
        AggregateLifecycle.apply(
            CreateRegisteredBankAccountAggregateEvent(
                command.membershipId,
                command.bankName,
                command.bankAccountNumber
            )
        )
    }

    @CommandHandler
    fun handle(@NotNull command: CheckRegisteredBankAccountCommand, externalBankAccountPort: ExternalBankAccountPort) {
        println("hello")
        id = command.aggregateIdentifier
        val requestSearchBankAccount = RequestSearchBankAccount(command.bankName, command.bankAccountNumber)
        val bankAccountInfo = externalBankAccountPort.bankAccountInfo(requestSearchBankAccount)
        val isValid = bankAccountInfo.isValid
        val firmBankingUUID = UUID.randomUUID().toString()

        AggregateLifecycle.apply(
            CheckedRegisteredBankAccountEvent(
                firmbankingAggregateIdentifier = firmBankingUUID,
                membershipId = command.membershipId,
                rechargeRequestId = command.rechargeRequestId,
                checkRegisteredBankAccountId = command.checkRegisteredBankAccountId,
                bankName = bankAccountInfo.bankName,
                bankAccountNumber = bankAccountInfo.bankAccountNumber,
                amount = command.amount,
                isChecked = isValid
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreateRegisteredBankAccountAggregateEvent) {
        id = UUID.randomUUID().toString()
        membershipId = event.membershipId
        bankName = event.bankName
        bankAccountNumber = event.bankAccountNumber
    }
}

