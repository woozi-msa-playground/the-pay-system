package com.practice.banking.domain

import com.practice.banking.domain.vo.BankAccountAggregateIdentifier
import com.practice.banking.domain.vo.BankAccountNumber
import com.practice.banking.domain.vo.BankAccountId
import com.practice.banking.domain.vo.MembershipId
import com.practice.banking.domain.vo.BankName
import com.practice.banking.domain.vo.LinkedStatusIsValid

data class BankAccount(
    val bankAccountId: String,
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedIsValid: Boolean,
    val bankAccountAggregateIdentifier: String
) {
    constructor(
        bankAccountId: BankAccountId,
        membershipId: MembershipId,
        bankName: BankName,
        bankAccountNumber: BankAccountNumber,
        linkedStatusIsValid: LinkedStatusIsValid,
        bankAccountAggregateIdentifier: BankAccountAggregateIdentifier
    ) : this(
        bankAccountId.BankAccountId,
        membershipId.membershipId.toString(),
        bankName.bankName,
        bankAccountNumber.bankAccountNumber,
        linkedStatusIsValid.linkedStatusIsValid,
        bankAccountAggregateIdentifier.bankAccountAggregateIdentifier
    )
}