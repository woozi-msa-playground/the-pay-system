package com.practice.banking.adapter.out.external.bank

import com.practice.banking.application.port.out.ExternalBankAccountPort
import com.pratice.common.ExternalSystemAdapter

@ExternalSystemAdapter
class ExternalBankAccountAdapter(
) : ExternalBankAccountPort {

    override fun bankAccountInfo(requestSearchBankAccount: RequestSearchBankAccount): ExternalBankAccount =
        ExternalBankAccount(requestSearchBankAccount.bankName, requestSearchBankAccount.bankAccount, true)
}