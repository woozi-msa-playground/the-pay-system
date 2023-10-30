package com.practice.money.domain

import com.practice.money.domain.vo.MemberMoneyId
import com.practice.money.domain.vo.MembershipId
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import java.time.LocalDateTime
import java.util.UUID

data class MemberMoneyChangingRequest(
    var membershipId: String,
    var moneyChangingType: MoneyChangingType,
    var moneyChangingStatus: MoneyChangingStatus,
    var moneyBalance: Int,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var uuid: UUID,
    var memberMoneyId: String
) {

    constructor(
        membershipId: MembershipId,
        moneyChangingType: MoneyChangingType,
        moneyChangingStatus: MoneyChangingStatus,
        moneyBalance: MoneyBalance,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime,
        uuid: UUID,
        memberMoneyId: MemberMoneyId,
    ) : this(
        membershipId.membershipId,
        moneyChangingType,
        moneyChangingStatus,
        moneyBalance.moneyBalance,
        createdAt,
        updatedAt,
        uuid,
        memberMoneyId.memberMoneyId
    )

    fun increaseMoneyBalance(moneyBalance: MoneyBalance): MemberMoneyChangingRequest = MemberMoneyChangingRequest(
        this.membershipId,
        MoneyChangingType.INCREASING,
        MoneyChangingStatus.SUCCEEDED,
        Math.addExact(this.moneyBalance, moneyBalance.moneyBalance),
        this.createdAt,
        this.updatedAt,
        this.uuid,
        this.memberMoneyId
    )
}
