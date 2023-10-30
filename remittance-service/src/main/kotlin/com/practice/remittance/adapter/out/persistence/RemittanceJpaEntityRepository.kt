package com.practice.remittance.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface RemittanceJpaEntityRepository : JpaRepository<RemittanceJpaEntity, Long> {
    fun findAllByFromMembershipId(fromMembershipId: String): List<RemittanceJpaEntity>
}