package com.practice.msa.application.port.`in`

import com.practice.msa.domain.Membership

fun interface ModifyMembershipUseCase {
    fun modifyMembership(command: ModifyMembershipCommand): Membership
}
