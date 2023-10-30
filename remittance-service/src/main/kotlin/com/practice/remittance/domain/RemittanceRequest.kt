package com.practice.remittance.domain

import com.practice.remittance.domain.vo.Amount
import com.practice.remittance.domain.vo.FromMembershipId
import com.practice.remittance.domain.vo.RemittanceRequestId
import com.practice.remittance.domain.vo.RemittanceStatus
import com.practice.remittance.domain.vo.RemittanceType
import com.practice.remittance.domain.vo.ToBankAccountNumber
import com.practice.remittance.domain.vo.ToBankName
import com.practice.remittance.domain.vo.ToMembershipId

data class RemittanceRequest(
    val remittanceRequestId: String,
    val fromMembershipId: String,
    var toMembershipId: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val remittanceType: Int,
    val amount: Int,
    var remittanceStatus: String
) {
    constructor(
        remittanceRequestId: RemittanceRequestId,
        fromMembershipId: FromMembershipId,
        toMembershipId: ToMembershipId,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        remittanceType: RemittanceType,
        amount: Amount,
        remittanceStatus: RemittanceStatus
    ) : this(
        remittanceRequestId.remittanceRequestId,
        fromMembershipId.fromMembershipId,
        toMembershipId.toMembershipId,
        toBankName.toBankName,
        toBankAccountNumber.toBankAccountNumber,
        remittanceType.remittanceType,
        amount.amount,
        remittanceStatus.remittanceStatus
    )
}