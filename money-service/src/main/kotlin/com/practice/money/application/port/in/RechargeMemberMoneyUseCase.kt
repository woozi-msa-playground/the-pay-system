package com.practice.money.application.port.`in`

import com.practice.money.application.port.`in`.command.RechargeMemberMoneyCommand

fun interface RechargeMemberMoneyUseCase {
    fun rechargeMoneyRequest(command: RechargeMemberMoneyCommand)
}