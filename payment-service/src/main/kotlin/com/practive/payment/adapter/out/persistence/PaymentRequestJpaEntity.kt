package com.practive.payment.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.ZonedDateTime
import java.util.UUID

@Table(name = "payment_request")
@Entity
class PaymentRequestJpaEntity(
    var requestMembershipId: String,
    var requestPrice: String,
    var franchiseId: String,
    var franchiseFeeRate: String,
    var paymentStatus: Int,
    var approvedAt: ZonedDateTime? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var paymentRequestId: Long? = null
)