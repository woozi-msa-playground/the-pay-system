package com.practice.remittance.application.port.out.membership

fun interface CheckMembershipIdPort {
    fun checkMembershipId(membershipId: Long): Boolean
}