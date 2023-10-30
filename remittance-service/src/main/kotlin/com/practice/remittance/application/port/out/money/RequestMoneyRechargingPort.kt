package com.practice.remittance.application.port.out.money

import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.MembershipId

fun interface RequestMoneyRechargingPort {
    fun requestRecharging(membershipId: MembershipId, amount: Amount): MoneyInfo
}