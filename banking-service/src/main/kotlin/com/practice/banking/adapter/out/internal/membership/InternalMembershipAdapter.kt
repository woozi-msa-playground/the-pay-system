package com.practice.banking.adapter.out.internal.membership

import com.practice.banking.application.port.out.RequestVerifyMembershipIdPort
import com.pratice.common.InternalSystemAdapter

@InternalSystemAdapter
class InternalMembershipAdapter : RequestVerifyMembershipIdPort {
    override fun checkMembershipId(membershipId: Long): Boolean = true
}