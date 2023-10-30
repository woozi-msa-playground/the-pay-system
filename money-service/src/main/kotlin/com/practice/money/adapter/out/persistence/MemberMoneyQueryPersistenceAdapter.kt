package com.practice.money.adapter.out.persistence

import com.practice.common.PersistenceAdapter
import com.practice.money.application.port.out.FindMemberMoneyOrNullPort
import com.practice.money.application.port.out.FindMemberMoneyPort
import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MemberMoney
import com.practice.money.domain.vo.MembershipId
import jakarta.persistence.EntityNotFoundException
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class MemberMoneyQueryPersistenceAdapter(
    private val springDataMemberMoneyJpaRepository: SpringDataMemberMoneyJpaRepository
) : FindMemberMoneyOrNullPort, FindMemberMoneyPort {

    @Transactional(readOnly = true)
    override fun findMemberMoneyOrNull(membershipId: MembershipId): MemberMoney? =
        springDataMemberMoneyJpaRepository.findByMembershipId(membershipId.membershipId.toLong())
            ?.let(MemberMoneyMapper::mapToEntityDomain)
            ?: null

    @Transactional(readOnly = true)
    override fun findMemberMoney(membershipId: MembershipId): MemberMoney =
        springDataMemberMoneyJpaRepository.findByMembershipId(membershipId.membershipId.toLong())
            ?.let(MemberMoneyMapper::mapToEntityDomain)
            ?: throw EntityNotFoundException()
}
