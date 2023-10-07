package com.practice.msa.application.port.`in`

import com.practice.msa.domain.Membership

fun interface RegisterMembershipUseCase {
    fun registerMembership(command: RegisterMembershipCommand): Membership
}