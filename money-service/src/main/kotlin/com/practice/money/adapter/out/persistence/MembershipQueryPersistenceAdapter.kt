package com.practice.money.adapter.out.persistence

import com.practice.money.application.port.out.FindMemberMoneyPort
import com.practice.money.domain.MemberMoney
import com.practice.money.domain.vo.MembershipId
import com.pratice.common.PersistenceAdapter
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class MembershipQueryPersistenceAdapter(
    private val springDataMemberMoneyJpaRepository: SpringDataMemberMoneyJpaRepository
) : FindMemberMoneyPort {

    @Transactional(readOnly = true)
    override fun findMemberMoney(
        membershipId: MembershipId,
    ): MemberMoney? =
        springDataMemberMoneyJpaRepository.findByMembershipId(membershipId.membershipId)?.let {
            return@findMemberMoney MemberMoneyMapper.mapToEntityDomain(it)
        } ?: null
}
