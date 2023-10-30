package com.practice.money.adapter.axon.saga

import com.practice.common.command.CheckRegisteredBankAccountCommand
import com.practice.common.command.RequestFirmBankingCommand
import com.practice.common.command.RollBackFirmBankingRequestCommand
import com.practice.common.event.CheckedRegisteredBankAccountEvent
import com.practice.common.event.RequestFirmBankingResultFinishedEvent
import com.practice.common.event.RollbackFirmBankingResultFinishedEvent
import com.practice.money.adapter.axon.event.RechargingRequestCreatedEvent
import com.practice.money.application.port.out.DecreaseMemberMoneyPort
import com.practice.money.application.port.out.IncreaseMemberMoneyPort
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.EndSaga
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Saga
class MoneyRechargeSaga{

    @Autowired
    @Transient
    private lateinit var commandGateway: CommandGateway


    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    fun handle(event: RechargingRequestCreatedEvent) {
        val checkRegisteredBankAccountId = UUID.randomUUID().toString()
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId)
        commandGateway?.send<Any>(
            CheckRegisteredBankAccountCommand(
                checkRegisteredBankAccountId = checkRegisteredBankAccountId,
                aggregateIdentifier = event.registeredBankAccountAggregateIdentifier,
                membershipId = event.membershipId,
                rechargeRequestId = event.rechargingRequestId,
                bankName = event.bankName,
                bankAccountNumber = event.bankAccountNumber,
                amount = event.amount
            )
        )?.whenComplete { result, throwable ->
            if (throwable != null) {
                println(throwable.printStackTrace())
                throw RuntimeException()
            }
            println(result)
        }
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    fun handle(event: CheckedRegisteredBankAccountEvent) {
        if (!event.isChecked) {
        } else {
        }
        val requestFirmBankingId = UUID.randomUUID().toString()
        SagaLifecycle.associateWith("requestFirmBankingId", requestFirmBankingId)

        val requestFirmBankingCommand = RequestFirmBankingCommand(
            requestFirmBankingId = requestFirmBankingId,
            aggregateIdentifier = event.firmbankingAggregateIdentifier,
            rechargeRequestId = event.rechargeRequestId,
            membershipId = event.membershipId,
            checkRegisteredBankAccountId = event.checkRegisteredBankAccountId,
            toBankName = "mirynag",
            toBankAccountNumber = "love",
            fromBankName = event.bankName,
            fromBankAccountNumber = event.bankAccountNumber,
            amount = event.amount
        )

        commandGateway?.send<Any>(requestFirmBankingCommand)?.whenCompleteAsync { result, throwable -> }
    }

    @SagaEventHandler(associationProperty = "requestFirmBankingId")
    fun handle(event: RequestFirmBankingResultFinishedEvent, increaseMemberMoneyPort: IncreaseMemberMoneyPort) {
        if (event.status == 200) {
        } else {
        }
        val memberMoneyChangingRequest = increaseMemberMoneyPort.increaseMoneyPort(
            membershipId = MembershipId(event.membershipId),
            moneyChangingType = MoneyChangingType.INCREASING,
            moneyChangingStatus = MoneyChangingStatus.SUCCEEDED,
            moneyBalance = MoneyBalance(event.amount)
        )
        if (memberMoneyChangingRequest.membershipId == "1") {
            val rollbackFirmBankingId = UUID.randomUUID().toString()
            SagaLifecycle.associateWith("rollbackFirmBankingId", rollbackFirmBankingId)

            val rollBackFirmBankingRequestCommand = RollBackFirmBankingRequestCommand(
                rollbackFirmBankingId = rollbackFirmBankingId,
                aggregateIdentifier = event.requestFirmBankingId,
                rechargeRequestId = event.rechargeRequestId,
                membershipId = event.membershipId,
                checkRegisteredBankAccountId = event.checkRegisteredBankAccountId,
                toBankName = event.fromBankName,
                toBankAccountNumber = event.fromBankAccountNumber,
                fromBankName = event.toBankName,
                fromBankAccountNumber = event.toBankAccountNumber,
                amount = event.amount
            )
            commandGateway.send<Any>(rollBackFirmBankingRequestCommand).whenComplete { result, throwable ->
                if (throwable != null) {
                    throw RuntimeException()
                }
            }
            return
        }
        SagaLifecycle.end()
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmBankingId")
    fun handle(event: RollbackFirmBankingResultFinishedEvent, decreaseMemberMoneyPort: DecreaseMemberMoneyPort) {
        if (event.status == 200) {
        } else {
        }

        decreaseMemberMoneyPort.decreaseMoneyPort(
            membershipId = MembershipId(event.membershipId),
            moneyChangingType = MoneyChangingType.INCREASING,
            moneyChangingStatus = MoneyChangingStatus.SUCCEEDED,
            moneyBalance = MoneyBalance(event.amount)
        )
    }
}