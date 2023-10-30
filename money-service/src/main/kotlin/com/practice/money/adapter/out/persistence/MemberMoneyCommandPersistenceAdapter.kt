package com.practice.money.adapter.out.persistence

import com.practice.common.PersistenceAdapter
import com.practice.money.application.port.out.CreateMemberMoneyPort
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyAggregateIdentifier
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class MemberMoneyCommandPersistenceAdapter(
    private val springDataMemberMoneyJpaRepository: SpringDataMemberMoneyJpaRepository
): CreateMemberMoneyPort {

    @Transactional
    override fun createMemberMoney(membershipId: MembershipId, moneyAggregateIdentifier: MoneyAggregateIdentifier) {
        springDataMemberMoneyJpaRepository.saveAndFlush(
            MemberMoneyJpaEntity(
                membershipId = membershipId.membershipId.toLong(),
                balance = 0,
                aggregateIdentifier = moneyAggregateIdentifier.moneyAggregateIdentifier
            )
        )
    }
}