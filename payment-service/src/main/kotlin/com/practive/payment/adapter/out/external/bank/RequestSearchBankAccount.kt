package com.practive.payment.adapter.out.external.bank

data class RequestSearchBankAccount(
    val bankName: String,
    val bankAccount: String
)