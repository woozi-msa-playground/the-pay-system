package com.practice.money.application.port.out

import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyAggregateIdentifier

fun interface CreateMemberMoneyPort {
    fun createMemberMoney(
        membershipId: MembershipId,
        moneyAggregateIdentifier: MoneyAggregateIdentifier
    )
}