package com.practive.payment.adapter.out.external.firmbank

class ExternalFirmBankingRequest(
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: Int
) {
}