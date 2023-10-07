package com.practice.msa.application.port.`in`

import com.practice.msa.domain.Membership

fun interface FindMembershipUseCase {
    fun findMembership(membershipId: Long): Membership
}