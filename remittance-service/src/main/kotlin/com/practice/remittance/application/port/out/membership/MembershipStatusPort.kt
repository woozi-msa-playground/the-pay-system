package com.practice.remittance.application.port.out.membership

import com.practice.remittance.domain.vo.MembershipId

fun interface MembershipStatusPort {
    fun membershipStatus(membershipId: MembershipId): MembershipStatus
}