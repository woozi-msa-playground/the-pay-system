package com.practice.money.adapter.out.persistence

import com.practice.common.PersistenceAdapter
import com.practice.money.application.port.out.DecreaseMemberMoneyPort
import com.practice.money.application.port.out.IncreaseMemberMoneyPort
import com.practice.money.application.port.out.InitializeMemberMoneyPort
import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@PersistenceAdapter
class MembershipCommandPersistenceAdapter(
    private val springDataMemberMoneyChangingRequestJpaRepository: SpringDataMemberMoneyChangingRequestJpaRepository
): InitializeMemberMoneyPort, IncreaseMemberMoneyPort, DecreaseMemberMoneyPort {

    @Transactional
    override fun initializeMemberMoney(
        membershipId: MembershipId,
        moneyChangingType: MoneyChangingType,
        moneyChangingStatus: MoneyChangingStatus,
        moneyBalance: MoneyBalance,
    ): MemberMoneyChangingRequest = MemberMoneyChangingMapper.mapToEntityDomain(
        springDataMemberMoneyChangingRequestJpaRepository.save(
            MemberMoneyChangingRequestJpaEntity(
                membershipId = membershipId.membershipId,
                moneyChangingType = moneyChangingType.moneyChangingType,
                moneyChangingStatus = moneyChangingStatus.moneyChangingStatus,
                moneyBalance = moneyBalance.moneyBalance,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                UUID.randomUUID(),
            )
        )
    )

    @Transactional
    override fun increaseMoneyPort(
        membershipId: MembershipId,
        moneyChangingType: MoneyChangingType,
        moneyChangingStatus: MoneyChangingStatus,
        moneyBalance: MoneyBalance,
    ): MemberMoneyChangingRequest = MemberMoneyChangingMapper.mapToEntityDomain(
        springDataMemberMoneyChangingRequestJpaRepository.save(
            MemberMoneyChangingRequestJpaEntity(
                membershipId = membershipId.membershipId,
                moneyChangingType = moneyChangingType.moneyChangingType,
                moneyChangingStatus = moneyChangingStatus.moneyChangingStatus,
                moneyBalance = moneyBalance.moneyBalance,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                UUID.randomUUID(),
            )
        )
    )

    @Transactional
    override fun decreaseMoneyPort(
        membershipId: MembershipId,
        moneyChangingType: MoneyChangingType,
        moneyChangingStatus: MoneyChangingStatus,
        moneyBalance: MoneyBalance
    ): MemberMoneyChangingRequest = MemberMoneyChangingMapper.mapToEntityDomain(
        springDataMemberMoneyChangingRequestJpaRepository.save(
            MemberMoneyChangingRequestJpaEntity(
                membershipId = membershipId.membershipId,
                moneyChangingType = moneyChangingType.moneyChangingType,
                moneyChangingStatus = moneyChangingStatus.moneyChangingStatus,
                moneyBalance = moneyBalance.moneyBalance,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                UUID.randomUUID(),
            )
        )
    )
}
