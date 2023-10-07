package com.practice.msa.application

import com.practice.msa.application.port.`in`.FindMembershipUseCase
import com.practice.msa.application.port.out.FindMembershipPort
import com.practice.msa.common.UseCase
import com.practice.msa.domain.Membership

@UseCase
class FindMembershipUseCaseImpl(
    private val findMembershipPort: FindMembershipPort
) : FindMembershipUseCase {

    override fun findMembership(membershipId: Long): Membership =
        findMembershipPort.findMembership(membershipId)
}