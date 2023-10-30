package com.practice.remittance.application.port.out.money

import com.practice.remittance.domain.vo.MembershipId

fun interface RequestMoneyInfoPort {
    fun moneyInfo(membershipId: MembershipId): MoneyInfo
}