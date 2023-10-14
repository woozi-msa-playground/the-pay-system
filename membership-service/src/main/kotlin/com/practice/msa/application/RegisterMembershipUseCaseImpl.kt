package com.practice.msa.application

import com.practice.msa.application.port.`in`.RegisterMembershipCommand
import com.practice.msa.application.port.`in`.RegisterMembershipUseCase
import com.practice.msa.application.port.out.RegisterMembershipPort
import com.practice.msa.domain.Membership
import com.practice.msa.domain.vo.MembershipAddress
import com.practice.msa.domain.vo.MembershipEmail
import com.practice.msa.domain.vo.MembershipIsCorp
import com.practice.msa.domain.vo.MembershipIsValid
import com.practice.msa.domain.vo.MembershipName
import com.practice.common.UseCase

@UseCase
class RegisterMembershipUseCaseImpl(
    private val membershipPort: RegisterMembershipPort
) : RegisterMembershipUseCase {
    override fun registerMembership(command: RegisterMembershipCommand): Membership =
        membershipPort.createMemberShip(
            MembershipName(command.name),
            MembershipEmail(command.email),
            MembershipAddress(command.address),
            MembershipIsValid(command.isValid),
            MembershipIsCorp(command.isCorp)
        )
}