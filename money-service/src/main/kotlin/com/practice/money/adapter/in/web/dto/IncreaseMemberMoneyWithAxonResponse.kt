package com.practice.money.adapter.`in`.web.dto

import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MoneyChangingType

data class IncreaseMemberMoneyWithAxonResponse(
    val result: Boolean,
    // val amount: Int,
    // val moneyChangingType: MoneyChangingType,
)
