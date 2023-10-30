package com.practice.money.application.port.`in`

import com.practice.money.application.port.`in`.command.IncreaseMemberMoneyCommand
import com.practice.money.domain.MemberMoneyChangingRequest

fun interface IncreaseMemberMoneyAsyncUseCase {
    fun increaseMoneyRequestAsync(command: IncreaseMemberMoneyCommand): MemberMoneyChangingRequest
}