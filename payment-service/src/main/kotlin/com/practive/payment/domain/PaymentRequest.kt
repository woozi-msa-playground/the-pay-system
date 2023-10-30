package com.practive.payment.domain

import com.practive.payment.domain.vo.ApprovedAt
import com.practive.payment.domain.vo.FranchiseFeeRate
import com.practive.payment.domain.vo.FranchiseId
import com.practive.payment.domain.vo.PaymentRequestId
import com.practive.payment.domain.vo.PaymentStatus
import com.practive.payment.domain.vo.RequestMembershipId
import com.practive.payment.domain.vo.RequestPrice
import java.time.ZonedDateTime

data class PaymentRequest(
    val paymentRequestId: String,
    val requestMembershipId: String,
    val requestPrice: String,
    val franchiseId: String,
    val franchiseFeeRate: String,
    val paymentStatus: Int,
    val approvedAt: ZonedDateTime? = null
) {
    constructor(
        paymentRequestId: PaymentRequestId,
        requestMembershipId: RequestMembershipId,
        requestPrice: RequestPrice,
        franchiseId: FranchiseId,
        franchiseFeeRate: FranchiseFeeRate,
        paymentStatus: PaymentStatus,
        approvedAt: ApprovedAt?
    ) : this(
        paymentRequestId = paymentRequestId.paymentRequestId,
        requestMembershipId = requestMembershipId.requestMembershipId,
        requestPrice = requestPrice.requestPrice,
        franchiseId = franchiseId.franchiseId,
        franchiseFeeRate = franchiseFeeRate.franchiseFeeRate,
        paymentStatus = paymentStatus.paymentStatus,
        approvedAt = approvedAt?.approvedAt
    )
}
