package com.practice.money.adapter.out.persistence

import com.practice.money.application.port.out.IncreaseMemberMoneyPort
import com.practice.money.application.port.out.InitializeMemberMoneyPort
import com.practice.money.domain.MemberMoney
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import com.pratice.common.PersistenceAdapter
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@PersistenceAdapter
class MembershipCommandPersistenceAdapter(
    private val springDataMemberMoneyJpaRepository: SpringDataMemberMoneyJpaRepository
) : InitializeMemberMoneyPort, IncreaseMemberMoneyPort {

    @Transactional
    override fun initializeMemberMoney(
        membershipId: MembershipId,
        moneyChangingType: MoneyChangingType,
        moneyChangingStatus: MoneyChangingStatus,
        moneyBalance: MoneyBalance,
    ): MemberMoney = MemberMoneyMapper.mapToEntityDomain(
        springDataMemberMoneyJpaRepository.save(
            MemberMoneyJpaEntity(
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
    override fun increaseMoneyPort(memberMoney: MemberMoney): MemberMoney =
        MemberMoneyMapper.mapToEntityDomain(
            springDataMemberMoneyJpaRepository.saveAndFlush(MemberMoneyMapper.mapToDomainEntity(memberMoney))
        )
}
