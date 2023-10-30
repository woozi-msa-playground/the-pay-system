package com.practice.remittance.application.port.out.banking

fun interface RequestBankingInfo {
    fun requestBankInfo(bankName: String, bankAccountNumber: String): FirmBankingInfo
}