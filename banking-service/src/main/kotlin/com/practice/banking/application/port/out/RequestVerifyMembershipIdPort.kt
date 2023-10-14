package com.practice.banking.application.port.out

fun interface RequestVerifyMembershipIdPort {
    fun checkMembershipId(membershipId: Long): Boolean
}