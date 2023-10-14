package com.practice.money.adapter.out.persistence

import com.practice.money.domain.MemberMoney
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MemberMoneyId
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import com.practice.money.domain.vo.MembershipId
import java.util.UUID

object MemberMoneyMapper {

    fun mapToDomainEntity(memberMoney: MemberMoney): MemberMoneyJpaEntity =
        MemberMoneyJpaEntity(
            membershipId = memberMoney.membershipId,
            moneyChangingType = memberMoney.moneyChangingType.moneyChangingType,
            moneyChangingStatus = memberMoney.moneyChangingStatus.moneyChangingStatus,
            moneyBalance = memberMoney.moneyBalance,
            createdAt = memberMoney.createdAt,
            updatedAt = memberMoney.updatedAt,
            uuid = UUID.randomUUID(),
            memberMoneyId = memberMoney.memberMoneyId.toLong()
        )

    fun mapToEntityDomain(entity: MemberMoneyJpaEntity): MemberMoney {
        return MemberMoney(
            membershipId = MembershipId(entity.membershipId),
            moneyChangingType = MoneyChangingType.of(entity.moneyChangingType),
            moneyChangingStatus = MoneyChangingStatus.of(entity.moneyChangingStatus),
            moneyBalance = MoneyBalance(entity.moneyBalance),
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            uuid = entity.uuid,
            memberMoneyId = MemberMoneyId(entity.memberMoneyId.toString())
        )
    }
}


