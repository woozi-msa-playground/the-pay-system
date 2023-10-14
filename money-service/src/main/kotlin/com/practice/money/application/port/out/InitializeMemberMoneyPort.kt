package com.practice.money.application.port.out

import com.practice.money.domain.MemberMoney
import com.practice.money.domain.vo.MoneyBalance
import com.practice.money.domain.vo.MoneyChangingStatus
import com.practice.money.domain.vo.MoneyChangingType
import com.practice.money.domain.vo.MembershipId

fun interface InitializeMemberMoneyPort {
    fun initializeMemberMoney(
        membershipId: MembershipId,
        moneyChangingType: MoneyChangingType,
        moneyChangingStatus: MoneyChangingStatus,
        moneyBalance: MoneyBalance,
    ): MemberMoney
}