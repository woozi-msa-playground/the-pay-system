package com.practice.money.adapter.`in`.web.dto

import com.practice.money.application.port.`in`.command.DecreaseMemberMoneyCommand

data class DecreaseMemberMoneyRequest(
    val membershipId: String,
    val amount: Int
) {
    fun toCommand(): DecreaseMemberMoneyCommand =
        DecreaseMemberMoneyCommand(membershipId, amount)
}
