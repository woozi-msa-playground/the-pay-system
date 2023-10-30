package com.practice.remittance.adapter.`in`.web

import com.practice.remittance.domain.RemittanceRequest

data class RequestRemittanceResponse(
    val remittanceRequestId: String,
    val fromMembershipId: String,
    val toMembershipId: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val amount: Int,
    val remittanceType: Int,
    val remittanceStatus: String
) {
    constructor(remittanceRequest: RemittanceRequest): this(
        remittanceRequest.remittanceRequestId,
        remittanceRequest.fromMembershipId,
        remittanceRequest.toMembershipId,
        remittanceRequest.toBankName,
        remittanceRequest.toBankAccountNumber,
        remittanceRequest.amount,
        remittanceRequest.remittanceType,
        remittanceRequest.remittanceStatus
    )
}