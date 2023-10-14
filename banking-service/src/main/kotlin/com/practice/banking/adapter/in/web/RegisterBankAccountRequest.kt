package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.BankAccountRegisterCommand

data class RegisterBankAccountRequest(
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedIsValid: Boolean,
) {
    fun toCommand(): BankAccountRegisterCommand =
        BankAccountRegisterCommand(membershipId.toLong(), bankName, bankAccountNumber, linkedIsValid)
}
