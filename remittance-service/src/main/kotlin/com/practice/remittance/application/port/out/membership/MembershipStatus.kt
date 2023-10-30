package com.practice.remittance.application.port.out.membership

data class MembershipStatus(
    val membershipId: String,
    val isValid: Boolean
)