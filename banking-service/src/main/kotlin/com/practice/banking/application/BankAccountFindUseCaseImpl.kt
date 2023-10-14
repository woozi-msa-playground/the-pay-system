package com.practice.banking.application

import com.practice.banking.application.port.`in`.BankAccountFindUseCase
import com.practice.banking.application.port.out.FindBankAccountPort
import com.practice.banking.domain.BankAccount
import com.pratice.common.UseCase

@UseCase
class BankAccountFindUseCaseImpl(
    private val findBankAccountPort: FindBankAccountPort
) : BankAccountFindUseCase {

    override fun findBankAccount(bankAccountId: Long): BankAccount =
        findBankAccountPort.findBankAccount(bankAccountId)
}