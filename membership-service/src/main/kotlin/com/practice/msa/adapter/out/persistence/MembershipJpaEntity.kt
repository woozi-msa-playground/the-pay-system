package com.practice.msa.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "membership")
@Entity
class MembershipJpaEntity(

    var name: String,
    var address: String,
    var email: String,
    var isValid: Boolean,
    var isCorp: Boolean,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var membershipId: Long? = null
) {

    override fun toString(): String {
        return "MembershipJpaEntity(name='$name', address='$address', email='$email', isValid=$isValid, isCorp=$isCorp, membershipId=$membershipId)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MembershipJpaEntity

        if (membershipId != other.membershipId) return false

        return true
    }

    override fun hashCode(): Int {
        return membershipId?.hashCode() ?: 0
    }
}