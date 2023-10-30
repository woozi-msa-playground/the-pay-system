package com.practice.banking.application.port.`in`

import com.practice.banking.domain.BankAccount

fun interface BankAccountFindEdaUseCase {
    fun findBankAccountEda(membershipId: Long): BankAccount
}