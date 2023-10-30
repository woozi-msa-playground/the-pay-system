package com.practice.money.application

import com.practice.common.CountdownLatchManager
import com.practice.common.RechargingMoneyTask
import com.practice.common.SubTask
import com.practice.common.UseCase
import com.practice.money.adapter.axon.command.MemberMoneyCreatedCommand
import com.practice.money.adapter.axon.command.RechargingMemberMoneyRequestCommand
import com.practice.money.application.port.`in`.AxonIncreaseMemberMoneyUseCase
import com.practice.money.application.port.`in`.CreateMemberMoneyUseCase
import com.practice.money.application.port.`in`.DecreaseMemberMoneyUseCase
import com.practice.money.application.port.`in`.IncreaseMemberMoneyAsyncUseCase
import com.practice.money.application.port.`in`.IncreaseMemberMoneyUseCase
import com.practice.money.application.port.`in`.command.CreateMemberMoneyCommand
import com.practice.money.application.port.`in`.command.DecreaseMemberMoneyCommand
import com.practice.money.application.port.`in`.command.IncreaseMemberMoneyCommand
import com.practice.money.application.port.out.CreateMemberMoneyPort
import com.practice.money.application.port.out.DecreaseMemberMoneyPort
import com.practice.money.application.port.out.FindMemberMoneyOrNullPort
import com.practice.money.application.port.out.FindMemberMoneyPort
import com.practice.money.application.port.out.IncreaseMemberMoneyPort
import com.practice.money.application.port.out.InitializeMemberMoneyPort
import com.practice.money.application.port.out.MembershipStatusPort
import com.practice.money.application.port.out.RechargingMoneyTaskPort
import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyAggregateIdentifier
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import org.axonframework.commandhandling.gateway.CommandGateway
import java.util.UUID

@UseCase
class ChangeMemberMoneyUseCaseImpl(
    private val membershipMoneyPort: MembershipStatusPort,
    private val findMemberMoneyOrNullPort: FindMemberMoneyOrNullPort,
    private val findMemberMoneyPort: FindMemberMoneyPort,
    private val rechargingMoneyTaskPort: RechargingMoneyTaskPort,
    private val createMemberMoneyPort: CreateMemberMoneyPort,
    private val initializeMemberMoneyPort: InitializeMemberMoneyPort,
    private val increaseMemberMoneyPort: IncreaseMemberMoneyPort,
    private val decreaseMemberMoneyPort: DecreaseMemberMoneyPort,
    private val countdownLatchManager: CountdownLatchManager,
    private val commandGateway: CommandGateway
) : CreateMemberMoneyUseCase, AxonIncreaseMemberMoneyUseCase, IncreaseMemberMoneyUseCase, IncreaseMemberMoneyAsyncUseCase, DecreaseMemberMoneyUseCase {

    override fun createMemberMoney(command: CreateMemberMoneyCommand): Boolean {
        val memberMoneyCreatedCommand = MemberMoneyCreatedCommand(command.membershipId)
        commandGateway.send<Any>(memberMoneyCreatedCommand).whenComplete { result, throwable ->
            if(throwable != null) {
                throw RuntimeException()
            }
            createMemberMoneyPort.createMemberMoney(
                MembershipId(command.membershipId),
                MoneyAggregateIdentifier(result.toString())
            )
        }
        return true
    }

    override fun rechargingMoney(command: IncreaseMemberMoneyCommand): Boolean {
        val membershipId = MembershipId(command.membershipId)
        val memberMoney = findMemberMoneyPort.findMemberMoney(membershipId)
        val rechargingMemberMoneyRequestCommand = RechargingMemberMoneyRequestCommand(
            memberMoney.aggregateIdentifier,
            UUID.randomUUID().toString(),
            command.membershipId,
            memberMoney.balance
        )
        commandGateway.send<Any>(rechargingMemberMoneyRequestCommand).whenComplete { result, throwable ->
            if(throwable != null) {
                throw RuntimeException()
            }
            println("result$result")
            increaseMemberMoneyPort.increaseMoneyPort(
                MembershipId(command.membershipId),
                MoneyChangingType.INCREASING,
                MoneyChangingStatus.SUCCEEDED,
                MoneyBalance(command.moneyBalance)
            )
        }

        // val memberMoneyIncreaseCommand = MemberMoneyIncreaseCommand(memberMoney.aggregateIdentifier, command.membershipId, command.moneyBalance)
        // commandGateway.send<String>(memberMoneyIncreaseCommand).whenComplete { result, throwable ->
        //     if(throwable != null) {
        //         throw RuntimeException()
        //     }
        //     println(result)
        //     increaseMemberMoneyPort.increaseMoneyPort(
        //         MembershipId(command.membershipId),
        //         MoneyChangingType.INCREASING,
        //         MoneyChangingStatus.SUCCEEDED,
        //         MoneyBalance(command.moneyBalance)
        //     )
        // }
        return true
    }


    override fun increaseMoneyRequest(command: IncreaseMemberMoneyCommand): MemberMoneyChangingRequest {
        val membershipStatus = membershipMoneyPort.membershipStatus(MembershipId(command.membershipId))
        if (!membershipStatus.isValid) {
            throw RuntimeException()
        }
        val memberMoney = findMemberMoneyPort.findMemberMoney(MembershipId(command.membershipId))
        return increaseMemberMoneyPort.increaseMoneyPort(
            MembershipId(memberMoney.membershipId.toString()),
            MoneyChangingType.INCREASING,
            MoneyChangingStatus.SUCCEEDED,
            MoneyBalance(command.moneyBalance)
        )
    }

    override fun increaseMoneyRequestAsync(command: IncreaseMemberMoneyCommand): MemberMoneyChangingRequest {
        val subTasks = listOf(
            SubTask(
                subTaskName = "validMemberTask : 멤버십 유효성 검사",
                membershipId = command.membershipId,
                taskType = "membership",
                status = "ready"
            ),
            SubTask(
                subTaskName = "validBankingAccountTask : 뱅킹 계좌 유효성 검사",
                membershipId = command.membershipId,
                taskType = "banking",
                status = "ready"
            ),
        )

        val task = RechargingMoneyTask(
            taskId = UUID.randomUUID().toString(),
            taskName = "Increase Money Task / 머니 충전 Task",
            subTaskList = subTasks,
            moneyAmount = command.moneyBalance,
            membershipId = command.membershipId,
            toBankName = "miryang",
            toBankAccountNumber = ""
        )
        rechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task)
        countdownLatchManager.addCountDownLatch(task.taskId)
        countdownLatchManager.countDownLatch(task.taskId).await()
        val result = countdownLatchManager.dataForKey(task.taskId)
        if (result == "success") {
            return increaseMemberMoneyPort.increaseMoneyPort(
                MembershipId(command.membershipId),
                MoneyChangingType.INCREASING,
                MoneyChangingStatus.SUCCEEDED,
                MoneyBalance(command.moneyBalance)
            )
        }
        throw RuntimeException()
    }

    override fun decreaseMoneyRequest(command: DecreaseMemberMoneyCommand): MemberMoneyChangingRequest {
        val membershipStatus = membershipMoneyPort.membershipStatus(MembershipId(command.membershipId))
        if (!membershipStatus.isValid) {
            throw RuntimeException()
        }
        val memberMoney = findMemberMoneyPort.findMemberMoney(MembershipId(command.membershipId))
        if (memberMoney.balance < command.moneyBalance) {
            throw RuntimeException("여기는 이제 리차징하는 걸로 헤야 함")
        }
        return decreaseMemberMoneyPort.decreaseMoneyPort(
            MembershipId(command.membershipId),
            MoneyChangingType.DECREASING,
            MoneyChangingStatus.SUCCEEDED,
            MoneyBalance(command.moneyBalance)
        )
    }

    // override fun rechargeMoneyRequest(command: RechargeMemberMoneyCommand): MemberMoney {
    //     val membershipStatus = membershipMoneyPort.membershipStatus(MembershipId(command.membershipId))
    //     if (!membershipStatus.isValid) {
    //         throw RuntimeException()
    //     }
    //     rechargingMoneyPort.sendRechargingMoneyPort(
    //         MembershipId(command.membershipId),
    //         MoneyBalance(command.moneyBalance)
    //     )
    //     return MemberMoney()
    // }
}