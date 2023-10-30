package com.practive.payment.adapter.out.external.bank

data class ExternalBankAccount(
    val bankName: String,
    val bankAccountNumber: String,
    val isValid: Boolean,
)
