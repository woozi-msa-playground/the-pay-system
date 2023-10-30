package com.practice.remittance.application.port.out.money

import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.MembershipId

fun interface RequestMoneyIncreasePort {
    fun requestMoneyIncrease(membershipId: MembershipId, amount: Amount): Boolean
}