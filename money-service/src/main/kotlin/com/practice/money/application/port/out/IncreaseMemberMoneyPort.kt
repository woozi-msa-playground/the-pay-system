package com.practice.money.application.port.out

import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType

fun interface IncreaseMemberMoneyPort {
    fun increaseMoneyPort(
        membershipId: MembershipId,
        moneyChangingType: MoneyChangingType,
        moneyChangingStatus: MoneyChangingStatus,
        moneyBalance: MoneyBalance,
    ): MemberMoneyChangingRequest
}