package com.practice.banking.application.port.out

data class MembershipStatus(
    val membershipId: String,
    val isValid: Boolean
)