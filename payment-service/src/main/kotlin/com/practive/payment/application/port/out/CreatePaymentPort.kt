package com.practive.payment.application.port.out

import com.practive.payment.domain.PaymentRequest
import com.practive.payment.domain.vo.FranchiseFeeRate
import com.practive.payment.domain.vo.FranchiseId
import com.practive.payment.domain.vo.PaymentStatus
import com.practive.payment.domain.vo.RequestMembershipId
import com.practive.payment.domain.vo.RequestPrice

fun interface CreatePaymentPort {
    fun createPayment(
        requestMembershipId: RequestMembershipId,
        requestPrice: RequestPrice,
        franchiseId: FranchiseId,
        franchiseFeeRate: FranchiseFeeRate,
        paymentStatus: PaymentStatus
    ): PaymentRequest
}