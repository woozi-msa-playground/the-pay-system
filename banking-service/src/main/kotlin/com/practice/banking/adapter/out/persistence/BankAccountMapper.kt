package com.practice.banking.adapter.out.persistence

import com.practice.banking.domain.BankAccount
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankAccountId
import com.practice.banking.domain.vo.MembershipId
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.LinkedStatusIsValid

object BankAccountMapper {

    fun mapToDomainEntity(bankAccount: BankAccount): BankAccountJpaEntity {
        return BankAccountJpaEntity(
            bankAccount.membershipId.toLong(),
            bankAccount.bankName,
            bankAccount.bankAccountNumber,
            bankAccount.linkedIsValid,
        )
    }

    fun mapToEntityDomain(entity: BankAccountJpaEntity): BankAccount {
        return BankAccount(
            BankAccountId(entity.bankAccountId.toString()),
            MembershipId(entity.membershipId.toString()),
            BankName(entity.bankName),
            BankAccountNumber(entity.bankAccountNumber),
            LinkedStatusIsValid(entity.linkedStatusIsValid),
        )
    }
}


