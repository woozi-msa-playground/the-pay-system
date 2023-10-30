package com.practice.money.adapter.out.persistence

import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MemberMoney
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MemberMoneyId
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import com.practice.money.domain.vo.MembershipId

object MemberMoneyMapper {

    fun mapToDomainEntity(memberMoney: MemberMoney): MemberMoneyJpaEntity =
        MemberMoneyJpaEntity(
            membershipId = memberMoney.membershipId,
            balance = memberMoney.balance,
            aggregateIdentifier = memberMoney.aggregateIdentifier,
            memberMoneyId = memberMoney.memberMoneyId.toLong()
        )

    fun mapToEntityDomain(entity: MemberMoneyJpaEntity): MemberMoney = MemberMoney(
        membershipId = entity.membershipId,
        balance = entity.balance,
        aggregateIdentifier = entity.aggregateIdentifier,
        memberMoneyId = entity.memberMoneyId.toString()
    )
}


