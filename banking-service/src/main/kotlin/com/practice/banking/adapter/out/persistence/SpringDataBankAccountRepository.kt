package com.practice.banking.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataBankAccountRepository : JpaRepository<BankAccountJpaEntity, Long> {
}