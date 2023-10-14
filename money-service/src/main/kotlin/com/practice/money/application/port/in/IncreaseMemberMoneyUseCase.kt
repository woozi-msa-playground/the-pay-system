package com.practice.money.application.port.`in`

import com.practice.money.domain.MemberMoney

fun interface IncreaseMemberMoneyUseCase {
    fun increaseMemberMoneyBalance(command: IncreaseMemberMoneyCommand): MemberMoney
}