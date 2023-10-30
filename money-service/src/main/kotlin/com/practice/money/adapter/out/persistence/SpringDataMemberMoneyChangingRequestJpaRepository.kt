package com.practice.money.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataMemberMoneyChangingRequestJpaRepository : JpaRepository<MemberMoneyChangingRequestJpaEntity, Long> {
    fun findByMembershipId(membershipId: String): MemberMoneyChangingRequestJpaEntity?
}