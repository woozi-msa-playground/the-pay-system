package com.practice.banking.adapter.out.external.bank

data class RequestSearchBankAccount(
    val bankName: String,
    val bankAccount: String
)