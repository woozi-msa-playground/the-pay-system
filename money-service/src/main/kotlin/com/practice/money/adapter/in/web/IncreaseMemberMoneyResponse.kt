package com.practice.money.adapter.`in`.web

import com.practice.money.domain.MemberMoney
import com.practice.money.domain.vo.MoneyChangingType

data class IncreaseMemberMoneyResponse(
    val memberMoneyId: String,
    val amount: Int,
    val moneyChangingType: MoneyChangingType,
) {
    constructor(memberMoney: MemberMoney) : this(
        memberMoney.memberMoneyId,
        memberMoney.moneyBalance,
        memberMoney.moneyChangingType
    )
}
