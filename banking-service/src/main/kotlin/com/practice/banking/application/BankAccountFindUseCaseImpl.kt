package com.practice.banking.application

import com.practice.banking.application.port.`in`.BankAccountFindEdaUseCase
import com.practice.banking.application.port.`in`.BankAccountFindUseCase
import com.practice.banking.application.port.out.FindBankAccountEdaPort
import com.practice.banking.application.port.out.FindBankAccountPort
import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.MembershipId
import com.practice.common.UseCase

@UseCase
class BankAccountFindUseCaseImpl(
    private val findBankAccountPort: FindBankAccountPort,
    private val findBankAccountEdaPort: FindBankAccountEdaPort,
) : BankAccountFindUseCase, BankAccountFindEdaUseCase {

    override fun findBankAccount(bankAccountId: Long): BankAccount =
        findBankAccountPort.findBankAccount(bankAccountId)

    override fun findBankAccountEda(membershipId: Long): BankAccount =
        findBankAccountEdaPort.findBankAccountEda(MembershipId(membershipId))
}