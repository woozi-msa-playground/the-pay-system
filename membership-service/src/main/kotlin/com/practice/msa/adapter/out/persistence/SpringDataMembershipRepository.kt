package com.practice.msa.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataMembershipRepository : JpaRepository<MembershipJpaEntity, Long> {
}