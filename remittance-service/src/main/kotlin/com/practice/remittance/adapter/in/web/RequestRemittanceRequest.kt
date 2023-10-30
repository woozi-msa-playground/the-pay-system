package com.practice.remittance.adapter.`in`.web

import com.practice.remittance.application.port.`in`.RemittanceRequestCommand

data class RequestRemittanceRequest(
    val fromMembershipId: String,
    val toMembershipId: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val amount: Int,
    val remittanceType: Int
) {
    fun toCommand(): RemittanceRequestCommand =
        RemittanceRequestCommand(fromMembershipId, toMembershipId, toBankName, toBankAccountNumber, amount, remittanceType)
}