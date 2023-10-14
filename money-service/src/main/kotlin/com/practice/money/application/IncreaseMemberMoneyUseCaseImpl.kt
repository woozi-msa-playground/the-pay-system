package com.practice.money.application

import com.practice.money.application.port.`in`.IncreaseMemberMoneyCommand
import com.practice.money.application.port.`in`.IncreaseMemberMoneyUseCase
import com.practice.money.application.port.out.FindMemberMoneyPort
import com.practice.money.application.port.out.IncreaseMemberMoneyPort
import com.practice.money.application.port.out.InitializeMemberMoneyPort
import com.practice.money.domain.MemberMoney
import com.practice.money.domain.vo.MemberMoneyId
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import com.practice.money.domain.vo.MembershipId
import com.pratice.common.UseCase

@UseCase
class IncreaseMemberMoneyUseCaseImpl(
    private val findMemberMoneyPort: FindMemberMoneyPort,
    private val initializeMemberMoneyPort: InitializeMemberMoneyPort,
    private val increaseMemberMoneyPort: IncreaseMemberMoneyPort
) : IncreaseMemberMoneyUseCase {

    override fun increaseMemberMoneyBalance(command: IncreaseMemberMoneyCommand): MemberMoney {
        val memberMoney = findMemberMoneyPort.findMemberMoney(MembershipId(command.membershipId))
            ?: initializeMemberMoneyPort.initializeMemberMoney(
                MembershipId(command.membershipId),
                MoneyChangingType.INCREASING,
                MoneyChangingStatus.REQUESTED,
                MoneyBalance(command.moneyBalance)
            )
        return increaseMemberMoneyPort.increaseMoneyPort(
            memberMoney.increaseMoneyBalance(MoneyBalance(command.moneyBalance))
        )
    }
}