package com.practice.money.application.port.out

import com.practice.money.domain.vo.MemberMoney
import com.practice.money.domain.vo.MembershipId

fun interface FindMemberMoneyOrNullPort {
    fun findMemberMoneyOrNull(membershipId: MembershipId): MemberMoney?
}