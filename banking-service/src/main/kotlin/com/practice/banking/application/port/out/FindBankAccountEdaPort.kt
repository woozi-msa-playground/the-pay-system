package com.practice.banking.application.port.out

import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountId
import com.practice.banking.domain.vo.MembershipId

fun interface FindBankAccountEdaPort {
    fun findBankAccountEda(membershipId: MembershipId): BankAccount
}