package com.practice.banking.application.port.out

import com.practice.banking.domain.vo.MembershipId

fun interface MembershipStatusPort {
    fun membershipStatus(membershipId: MembershipId): MembershipStatus
}