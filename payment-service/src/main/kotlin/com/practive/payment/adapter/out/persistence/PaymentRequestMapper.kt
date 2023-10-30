package com.practive.payment.adapter.out.persistence

import com.practive.payment.domain.PaymentRequest
import com.practive.payment.domain.vo.ApprovedAt
import com.practive.payment.domain.vo.FranchiseFeeRate
import com.practive.payment.domain.vo.FranchiseId
import com.practive.payment.domain.vo.PaymentRequestId
import com.practive.payment.domain.vo.PaymentStatus
import com.practive.payment.domain.vo.RequestMembershipId
import com.practive.payment.domain.vo.RequestPrice
import org.springframework.stereotype.Component

@Component
class PaymentRequestMapper {
    fun mapToEntityDomain(entity: PaymentRequestJpaEntity): PaymentRequest {
        return PaymentRequest(
            PaymentRequestId(entity.paymentRequestId.toString()),
            RequestMembershipId(entity.requestMembershipId),
            RequestPrice(entity.requestPrice),
            FranchiseId(entity.franchiseId),
            FranchiseFeeRate(entity.franchiseFeeRate),
            PaymentStatus(entity.paymentStatus),
            ApprovedAt(entity.approvedAt)
        )
    }
}


