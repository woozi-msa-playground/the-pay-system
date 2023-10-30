package com.practice.banking.adapter.out.persistence

import com.practice.banking.domain.vo.MembershipId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataBankAccountRepository : JpaRepository<BankAccountJpaEntity, Long> {
    fun findByMembershipId(membershipId: Long): BankAccountJpaEntity?
}