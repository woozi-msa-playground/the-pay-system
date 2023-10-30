package com.practive.payment.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataPaymentRequestRepository : JpaRepository<PaymentRequestJpaEntity, Long> {
}