package com.practice.money.adapter.`in`.web.dto

import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MoneyChangingType

data class RechargeMemberMoneyResponse(
    val memberMoneyId: String,
    val amount: Int,
    val moneyChangingType: MoneyChangingType,
) {
    constructor(memberMoneyChangingRequest: MemberMoneyChangingRequest) : this(
        memberMoneyChangingRequest.memberMoneyId,
        memberMoneyChangingRequest.moneyBalance,
        memberMoneyChangingRequest.moneyChangingType
    )
}
