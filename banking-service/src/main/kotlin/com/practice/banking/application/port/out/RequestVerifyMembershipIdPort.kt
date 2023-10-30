package com.practice.banking.application.port.out

import com.practice.banking.domain.vo.MembershipId

fun interface RequestVerifyMembershipIdPort {
    fun checkMembershipId(membershipId: MembershipId): Boolean
}