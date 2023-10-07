package com.practice.msa.application.port.out

import com.practice.msa.domain.Membership
import com.practice.msa.domain.vo.MembershipId

fun interface FindMembershipPort {

    fun findMembership(membershipId: Long): Membership =
        findMembership(MembershipId(membershipId.toString()))

    fun findMembership(membershipId: MembershipId): Membership
}