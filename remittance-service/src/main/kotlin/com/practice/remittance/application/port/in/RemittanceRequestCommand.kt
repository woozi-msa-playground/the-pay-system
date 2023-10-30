package com.practice.remittance.application.port.`in`

import com.practice.common.SelfValidating

data class RemittanceRequestCommand(
    val fromMembershipId: String,
    val toMembershipId: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val amount: Int,
    val remittanceType: Int
): SelfValidating<RemittanceRequestCommand>() {
    init {
        validateSelf()
    }
}

