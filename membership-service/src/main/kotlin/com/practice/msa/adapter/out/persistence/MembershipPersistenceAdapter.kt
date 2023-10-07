package com.practice.msa.adapter.out.persistence

import com.practice.msa.application.port.out.FindMembershipPort
import com.practice.msa.application.port.out.ModifyMembershipPort
import com.practice.msa.application.port.out.RegisterMembershipPort
import com.practice.msa.domain.Membership
import com.practice.msa.domain.vo.MembershipAddress
import com.practice.msa.domain.vo.MembershipEmail
import com.practice.msa.domain.vo.MembershipId
import com.practice.msa.domain.vo.MembershipIsCorp
import com.practice.msa.domain.vo.MembershipIsValid
import com.practice.msa.domain.vo.MembershipName
import com.pratice.common.PersistenceAdapter
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@PersistenceAdapter
class MembershipPersistenceAdapter(
    private val springDataMembershipRepository: SpringDataMembershipRepository
) : RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    @Transactional
    override fun createMemberShip(
        membershipName: MembershipName,
        membershipEmail: MembershipEmail,
        membershipAddress: MembershipAddress,
        membershipIsValid: MembershipIsValid,
        membershipIsCorp: MembershipIsCorp
    ): Membership = MembershipMapper.mapToEntityDomain(
        springDataMembershipRepository.save(
            MembershipJpaEntity(
                membershipName.name,
                membershipAddress.address,
                membershipEmail.email,
                membershipIsValid.isValid,
                membershipIsCorp.isCorp
            )
        )
    )

    @Transactional(readOnly = true)
    override fun findMembership(membershipId: MembershipId): Membership = MembershipMapper.mapToEntityDomain(
        springDataMembershipRepository.findByIdOrNull(membershipId.membershipId.toLong())
            ?: throw EntityNotFoundException()
    )

    @Transactional
    override fun modifyMemberShip(
        membershipId: MembershipId,
        membershipName: MembershipName,
        membershipEmail: MembershipEmail,
        membershipAddress: MembershipAddress,
        membershipIsValid: MembershipIsValid,
        membershipIsCorp: MembershipIsCorp
    ): Membership {
        val membershipJpaEntity = springDataMembershipRepository.findByIdOrNull(membershipId.membershipId.toLong())
            ?: throw EntityNotFoundException()
        membershipJpaEntity.name = membershipName.name
        membershipJpaEntity.email = membershipEmail.email
        membershipJpaEntity.address = membershipAddress.address
        membershipJpaEntity.isValid = membershipIsValid.isValid
        membershipJpaEntity.isCorp = membershipIsCorp.isCorp
        return MembershipMapper.mapToEntityDomain(
            springDataMembershipRepository.saveAndFlush(membershipJpaEntity)
        )
    }
}
