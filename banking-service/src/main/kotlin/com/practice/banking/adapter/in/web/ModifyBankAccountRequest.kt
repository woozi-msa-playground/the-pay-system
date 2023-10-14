package com.practice.banking.adapter.`in`.web

import com.practice.banking.application.port.`in`.BankAccountModifyCommand

data class ModifyBankAccountRequest(
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean
) {
    fun toCommand(bankAccountId: Long): BankAccountModifyCommand =
        BankAccountModifyCommand(bankAccountId, membershipId.toLong(), bankName, bankAccountNumber, linkedStatusIsValid)
}
