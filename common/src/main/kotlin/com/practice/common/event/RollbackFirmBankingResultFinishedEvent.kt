package com.practice.common.event

data class RollbackFirmBankingResultFinishedEvent(
    val rollbackFirmBankingId: String,
    val rechargeRequestId: String,
    val membershipId: String,
    val checkRegisteredBankAccountId: String,
    val status: Int,
    val toBankName: String,
    val toBankAccountNumber: String,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val amount: Int
) {
}