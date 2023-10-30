package com.practive.payment.adapter.out.persistence

import com.practice.common.PersistenceAdapter
import com.practive.payment.application.port.out.CreatePaymentPort
import com.practive.payment.domain.PaymentRequest
import com.practive.payment.domain.vo.FranchiseFeeRate
import com.practive.payment.domain.vo.FranchiseId
import com.practive.payment.domain.vo.PaymentStatus
import com.practive.payment.domain.vo.RequestMembershipId
import com.practive.payment.domain.vo.RequestPrice

@PersistenceAdapter
class PaymentRequestPersistenceAdapter(
    private val paymentRequestMapper: PaymentRequestMapper,
    private val springDataPaymentRequestRepository: SpringDataPaymentRequestRepository
) : CreatePaymentPort {

    override fun createPayment(
        requestMembershipId: RequestMembershipId,
        requestPrice: RequestPrice,
        franchiseId: FranchiseId,
        franchiseFeeRate: FranchiseFeeRate,
        paymentStatus: PaymentStatus,
    ): PaymentRequest =
        springDataPaymentRequestRepository.save(
            PaymentRequestJpaEntity(
                requestPrice = requestPrice.requestPrice,
                requestMembershipId = requestMembershipId.requestMembershipId,
                franchiseId = franchiseId.franchiseId,
                franchiseFeeRate = franchiseFeeRate.franchiseFeeRate,
                paymentStatus = paymentStatus.paymentStatus
            )
        ).let(paymentRequestMapper::mapToEntityDomain)
}
