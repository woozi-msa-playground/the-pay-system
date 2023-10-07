package com.practice.msa.adapter.out.persistence

import com.practice.msa.domain.Membership
import com.practice.msa.domain.vo.MembershipAddress
import com.practice.msa.domain.vo.MembershipEmail
import com.practice.msa.domain.vo.MembershipId
import com.practice.msa.domain.vo.MembershipIsCorp
import com.practice.msa.domain.vo.MembershipIsValid
import com.practice.msa.domain.vo.MembershipName

object MembershipMapper {

    fun mapToDomainEntity(membership: Membership): MembershipJpaEntity {
        return MembershipJpaEntity(
            membership.name,
            membership.email,
            membership.address,
            membership.isValid,
            membership.isCorp
        )
    }

    fun mapToEntityDomain(entity: MembershipJpaEntity): Membership {
        return Membership(
            MembershipId(entity.membershipId.toString()),
            MembershipName(entity.name),
            MembershipEmail(entity.email),
            MembershipAddress(entity.address),
            MembershipIsValid(entity.isValid),
            MembershipIsCorp(entity.isCorp)
        )
    }
}


