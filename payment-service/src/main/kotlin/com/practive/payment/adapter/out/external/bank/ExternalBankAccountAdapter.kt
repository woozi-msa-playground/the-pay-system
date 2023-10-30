package com.practive.payment.adapter.out.external.bank

import com.practice.banking.application.port.out.ExternalBankAccountPort
import com.practice.common.ExternalSystemAdapter

@ExternalSystemAdapter
class ExternalBankAccountAdapter(
) : ExternalBankAccountPort {

    override fun bankAccountInfo(requestSearchBankAccount: RequestSearchBankAccount): ExternalBankAccount =
        ExternalBankAccount(requestSearchBankAccount.bankName, requestSearchBankAccount.bankAccount, true)
}