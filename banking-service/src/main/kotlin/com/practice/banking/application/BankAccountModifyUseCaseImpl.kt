package com.practice.banking.application

import com.practice.banking.application.port.`in`.BankAccountModifyCommand
import com.practice.banking.application.port.`in`.BankAccountModifyUseCase
import com.practice.banking.application.port.out.ModifyBankAccountPort
import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.LinkedStatusIsValid
import com.practice.banking.domain.vo.MembershipId
import com.practice.banking.domain.vo.BankAccountId
import com.practice.common.UseCase

@UseCase
class BankAccountModifyUseCaseImpl(
    private val modifyBankAccountPort: ModifyBankAccountPort
) : BankAccountModifyUseCase {

    override fun modifyBankAccount(command: BankAccountModifyCommand): BankAccount =
        modifyBankAccountPort.modifyBankAccount(
            BankAccountId(command.bankAccountId.toString()),
            MembershipId(command.membershipId.toString()),
            BankName(command.bankName),
            BankAccountNumber(command.bankAccount),
            LinkedStatusIsValid(command.linkedStatusIsValid)
        )
}