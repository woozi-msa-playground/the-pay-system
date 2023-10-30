package com.practice.money.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "member_money")
@Entity
class MemberMoneyJpaEntity(

    var membershipId: Long,
    var balance: Int,
    var aggregateIdentifier: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberMoneyId: Long? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberMoneyJpaEntity

        if (membershipId != other.membershipId) return false

        return true
    }

    override fun hashCode(): Int {
        return membershipId.hashCode()
    }

    override fun toString(): String {
        return "MemberMoneyJpaEntity(membershipId=$membershipId, balance=$balance, aggregateIdentifier='$aggregateIdentifier', memberMoneyId=$memberMoneyId)"
    }
}