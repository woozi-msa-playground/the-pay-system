package com.practice.money.application.port.out

import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyBalance

fun interface RechargingMoneyPort {
    fun sendRechargingMoneyPort(
        membershipId: MembershipId,
        moneyBalance: MoneyBalance,
    )
}
