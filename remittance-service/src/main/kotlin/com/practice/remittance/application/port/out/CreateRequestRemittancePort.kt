package com.practice.remittance.application.port.out

import com.practice.remittance.domain.RemittanceRequest
import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.FromMembershipId
import com.practice.remittance.domain.vo.RemittanceType
import com.practice.remittance.domain.vo.ToBankAccountNumber
import com.practice.remittance.domain.vo.ToBankName
import com.practice.remittance.domain.vo.ToMembershipId

fun interface CreateRequestRemittancePort {
    fun createRemittanceRequestHistory(
        fromMembershipId: FromMembershipId,
        toMembershipId: ToMembershipId,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        amount: Amount,
        remittanceType: RemittanceType
    ): RemittanceRequest
}