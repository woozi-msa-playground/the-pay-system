package com.practice.money.adapter.axon.aggregate

import com.practice.money.adapter.axon.command.MemberMoneyCreatedCommand
import com.practice.money.adapter.axon.command.MemberMoneyIncreaseCommand
import com.practice.money.adapter.axon.command.RechargingMemberMoneyRequestCommand
import com.practice.money.adapter.axon.event.MemberMoneyCreatedEvent
import com.practice.money.adapter.axon.event.MemberMoneyIncreaseEvent
import com.practice.money.adapter.axon.event.RechargingRequestCreatedEvent
import com.practice.money.application.port.out.FindRegisteredBankAccountPort
import com.practice.money.domain.vo.MembershipId
import jakarta.validation.constraints.NotNull
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.hibernate.loader.ast.spi.EntityLoader
import org.hibernate.persister.entity.EntityPersister
import java.util.UUID

@Aggregate
data class MemberMoneyAggregate(
    @AggregateIdentifier
    var id: String? = null,
    var membershipId: Long? = null,
    var balance: Int? = null,
) {


    @CommandHandler
    constructor(command: MemberMoneyCreatedCommand) : this() {
        AggregateLifecycle.apply(MemberMoneyCreatedEvent(command.membershipId))
    }

    @CommandHandler
    fun handle(@NotNull command: MemberMoneyIncreaseCommand): String {
        this.id = command.id
        AggregateLifecycle.apply(MemberMoneyIncreaseEvent(command.id, command.membershipId, command.balance))
        return this.id!!
    }

    @CommandHandler
    fun handle(@NotNull command: RechargingMemberMoneyRequestCommand, registeredBankAccountPort: FindRegisteredBankAccountPort) {
        id = command.aggregateIdentifier
        val registeredBankAccountAggregate =
            registeredBankAccountPort.findRegisteredBankAccountAggregateIdentifier(MembershipId(command.membershipId))
        println("command.aggregateIdentifier = ${command.aggregateIdentifier}")
        println("registeredBankAccountAggregate = ${registeredBankAccountAggregate.aggregateIdentifier}")

        // saga start
        AggregateLifecycle.apply(RechargingRequestCreatedEvent(
            rechargingRequestId = command.aggregateIdentifier,
            membershipId = command.membershipId,
            amount = command.amount,
            registeredBankAccountAggregateIdentifier = registeredBankAccountAggregate.aggregateIdentifier,
            bankName = registeredBankAccountAggregate.bankName,
            bankAccountNumber=registeredBankAccountAggregate.bankAccountNumber
        ))
    }


    @EventSourcingHandler
    fun on(memberMoneyCreatedEvent: MemberMoneyCreatedEvent) {
        this.id = UUID.randomUUID().toString()
        this.membershipId = memberMoneyCreatedEvent.membershipId.toLong()
        this.balance = 0
    }

    @EventSourcingHandler
    fun on(memberMoneyIncreaseEvent: MemberMoneyIncreaseEvent) {
        this.id = memberMoneyIncreaseEvent.id
        this.membershipId = memberMoneyIncreaseEvent.membershipId?.toLong()
        this.balance = memberMoneyIncreaseEvent.balance
    }
}