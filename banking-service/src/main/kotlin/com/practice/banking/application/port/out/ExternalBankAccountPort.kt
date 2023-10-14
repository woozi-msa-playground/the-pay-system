package com.practice.banking.application.port.out

import com.practice.banking.adapter.out.external.bank.RequestSearchBankAccount
import com.practice.banking.adapter.out.external.bank.ExternalBankAccount

fun interface ExternalBankAccountPort {
    fun bankAccountInfo(requestSearchBankAccount: RequestSearchBankAccount): ExternalBankAccount
}