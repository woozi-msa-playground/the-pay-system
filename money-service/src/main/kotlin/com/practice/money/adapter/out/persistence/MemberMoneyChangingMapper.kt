package com.practice.money.adapter.out.persistence

import com.practice.money.domain.MemberMoneyChangingRequest
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MemberMoneyId
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import com.practice.money.domain.vo.MembershipId
import java.util.UUID

object MemberMoneyChangingMapper {

    fun mapToDomainEntity(memberMoneyChangingRequest: MemberMoneyChangingRequest): MemberMoneyChangingRequestJpaEntity =
        MemberMoneyChangingRequestJpaEntity(
            membershipId = memberMoneyChangingRequest.membershipId,
            moneyChangingType = memberMoneyChangingRequest.moneyChangingType.moneyChangingType,
            moneyChangingStatus = memberMoneyChangingRequest.moneyChangingStatus.moneyChangingStatus,
            moneyBalance = memberMoneyChangingRequest.moneyBalance,
            createdAt = memberMoneyChangingRequest.createdAt,
            updatedAt = memberMoneyChangingRequest.updatedAt,
            uuid = UUID.randomUUID()
        )

    fun mapToEntityDomain(entity: MemberMoneyChangingRequestJpaEntity): MemberMoneyChangingRequest {
        return MemberMoneyChangingRequest(
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


