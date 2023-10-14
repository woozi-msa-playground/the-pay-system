package com.practice.banking.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "bank_accounts")
@Entity
class BankAccountJpaEntity(

    var membershipId: Long,
    var bankName: String,
    var bankAccountNumber: String,
    var linkedStatusIsValid: Boolean,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bankAccountId: Long? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BankAccountJpaEntity

        if (bankAccountId != other.bankAccountId) return false

        return true
    }

    override fun hashCode(): Int {
        return bankAccountId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "BankAccountJpaEntity(membershipId=$membershipId, bankName='$bankName', bankAccountNumber='$bankAccountNumber', linkedStatusIsValid=$linkedStatusIsValid, bankAccountId=$bankAccountId)"
    }
}