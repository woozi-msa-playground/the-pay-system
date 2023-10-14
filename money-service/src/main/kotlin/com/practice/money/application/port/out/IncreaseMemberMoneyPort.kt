package com.practice.money.application.port.out

import com.practice.money.domain.MemberMoney

fun interface IncreaseMemberMoneyPort {
    fun increaseMoneyPort(memberMoneyId: MemberMoney): MemberMoney
}