package com.practice.money.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Table(name = "member_money_changing_request")
@Entity
class MemberMoneyChangingRequestJpaEntity(

    var membershipId: String,
    var moneyChangingType: Int,
    var moneyChangingStatus: Int,
    var moneyBalance: Int,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var uuid: UUID,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberMoneyId: Long? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberMoneyChangingRequestJpaEntity

        if (memberMoneyId != other.memberMoneyId) return false

        return true
    }

    override fun hashCode(): Int {
        return memberMoneyId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "MemberMoneyJpaEntity(membershipId='$membershipId', moneyChangingType=$moneyChangingType, moneyChangingStatus=$moneyChangingStatus, moneyBalance=$moneyBalance, createdAt=$createdAt, updatedAt=$updatedAt, uuid=$uuid, memberMoneyId=$memberMoneyId)"
    }
}