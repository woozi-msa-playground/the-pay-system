package com.practice.money.application.port.out

import com.practice.money.domain.vo.MembershipId

fun interface MembershipStatusPort {
    fun membershipStatus(membershipId: MembershipId): MembershipStatus
}