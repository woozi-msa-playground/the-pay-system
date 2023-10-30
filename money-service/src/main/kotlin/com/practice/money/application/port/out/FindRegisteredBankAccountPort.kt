package com.practice.money.application.port.out

import com.practice.money.domain.vo.MembershipId

fun interface FindRegisteredBankAccountPort {
    fun findRegisteredBankAccountAggregateIdentifier(membershipId: MembershipId): RegisteredBankAccountAggregate
}