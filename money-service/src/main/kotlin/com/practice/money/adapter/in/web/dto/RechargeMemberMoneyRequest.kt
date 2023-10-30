package com.practice.money.adapter.`in`.web.dto

import com.practice.money.application.port.`in`.command.DecreaseMemberMoneyCommand
import com.practice.money.application.port.`in`.command.RechargeMemberMoneyCommand

data class RechargeMemberMoneyRequest(
    val membershipId: String,
    val amount: Int
) {
    fun toCommand(): RechargeMemberMoneyCommand =
        RechargeMemberMoneyCommand(membershipId, amount)
}
