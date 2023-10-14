package com.practice.money.application.port.out

import com.practice.money.domain.MemberMoney
import com.practice.money.domain.vo.MembershipId

fun interface FindMemberMoneyPort {
    fun findMemberMoney(
        membershipId: MembershipId,
    ): MemberMoney?
}