package com.practice.msa.adapter.`in`.web

import com.practice.msa.domain.Membership

data class FindMembershipResponse(
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp: Boolean,
) {
    constructor(membership: Membership) : this(
        membership.name,
        membership.email,
        membership.address,
        membership.isValid,
        membership.isCorp
    )
}
