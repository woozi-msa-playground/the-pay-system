package com.practice.money.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataMemberMoneyJpaRepository : JpaRepository<MemberMoneyJpaEntity, Long> {
    fun findByMembershipId(membershipId: Long): MemberMoneyJpaEntity?
}