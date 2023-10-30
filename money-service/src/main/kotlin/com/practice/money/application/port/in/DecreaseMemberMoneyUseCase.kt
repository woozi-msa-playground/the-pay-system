package com.practice.money.application.port.`in`

import com.practice.money.application.port.`in`.command.DecreaseMemberMoneyCommand
import com.practice.money.domain.MemberMoneyChangingRequest

fun interface DecreaseMemberMoneyUseCase {
    fun decreaseMoneyRequest(command: DecreaseMemberMoneyCommand): MemberMoneyChangingRequest
}