package com.practice.banking.application.port.out

import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.MembershipId
import com.practice.banking.domain.vo.LinkedStatusIsValid

fun interface RegisterBankAccountPort {
    fun createBankAccount(
        membershipId: MembershipId,
        bankName: BankName,
        bankAccountNumber: BankAccountNumber,
        linkedStatusIsValid: LinkedStatusIsValid,
    ): BankAccount
}