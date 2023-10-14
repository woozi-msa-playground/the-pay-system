package com.practice.banking.application.port.out

import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.LinkedStatusIsValid
import com.practice.banking.domain.vo.MembershipId
import com.practice.banking.domain.vo.BankAccountId

fun interface ModifyBankAccountPort {
    fun modifyBankAccount(
        bankAccountId: BankAccountId,
        membershipId: MembershipId,
        bankName: BankName,
        bankAccountNumber: BankAccountNumber,
        linkedStatusIsValid: LinkedStatusIsValid
    ): BankAccount
}