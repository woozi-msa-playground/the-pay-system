package com.practice.msa.application

import com.practice.msa.application.port.`in`.ModifyMembershipCommand
import com.practice.msa.application.port.`in`.ModifyMembershipUseCase
import com.practice.msa.application.port.out.ModifyMembershipPort
import com.practice.msa.domain.Membership
import com.practice.msa.domain.vo.MembershipAddress
import com.practice.msa.domain.vo.MembershipEmail
import com.practice.msa.domain.vo.MembershipId
import com.practice.msa.domain.vo.MembershipIsCorp
import com.practice.msa.domain.vo.MembershipIsValid
import com.practice.msa.domain.vo.MembershipName
import com.pratice.common.UseCase

@UseCase
class ModifyMembershipUseCaseImpl(
    private val membershipPort: ModifyMembershipPort
) : ModifyMembershipUseCase {

    override fun modifyMembership(command: ModifyMembershipCommand): Membership =
        membershipPort.modifyMemberShip(
            MembershipId(command.id.toString()),
            MembershipName(command.name),
            MembershipEmail(command.email),
            MembershipAddress(command.address),
            MembershipIsValid(command.isValid),
            MembershipIsCorp(command.isCorp)
        )
}