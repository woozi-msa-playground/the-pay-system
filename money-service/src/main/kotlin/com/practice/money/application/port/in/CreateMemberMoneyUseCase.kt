package com.practice.money.application.port.`in`

import com.practice.money.application.port.`in`.command.CreateMemberMoneyCommand

fun interface CreateMemberMoneyUseCase {
    fun createMemberMoney(createMemberMoneyCommand: CreateMemberMoneyCommand): Boolean
}