package com.practice.banking.application

import com.practice.banking.adapter.out.external.bank.RequestSearchBankAccount
import com.practice.banking.application.port.`in`.BankAccountRegisterCommand
import com.practice.banking.application.port.`in`.BankAccountRegisterUseCase
import com.practice.banking.application.port.out.RegisterBankAccountPort
import com.practice.banking.application.port.out.ExternalBankAccountPort
import com.practice.banking.application.port.out.RequestVerifyMembershipIdPort
import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.LinkedStatusIsValid
import com.practice.banking.domain.vo.MembershipId
import com.practice.common.UseCase

@UseCase
class BankAccountRegisterUseCaseImpl(
    private val registerBankAccountPort: RegisterBankAccountPort,
    private val externalBankAccountPort: ExternalBankAccountPort,
    private val requestVerifyMembershipIdPort: RequestVerifyMembershipIdPort
) : BankAccountRegisterUseCase {

    override fun registerBankAccount(command: BankAccountRegisterCommand): BankAccount {
        val isMembershipId = requestVerifyMembershipIdPort.checkMembershipId(command.membershipId)
        if (!isMembershipId) {
            throw RuntimeException()
        }
        val externalBankAccount = externalBankAccountPort.bankAccountInfo(
            RequestSearchBankAccount(
                command.bankName,
                command.bankAccount
            )
        )
        if (!externalBankAccount.isValid) {
            throw RuntimeException()
        }
        return registerBankAccountPort.createBankAccount(
            MembershipId(command.membershipId.toString()),
            BankName(command.bankName),
            BankAccountNumber(command.bankAccount),
            LinkedStatusIsValid(command.linkedStatusIsValid)
        )
    }
}
