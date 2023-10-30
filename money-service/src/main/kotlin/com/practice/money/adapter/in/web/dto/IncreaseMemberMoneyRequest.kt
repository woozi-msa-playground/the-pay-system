package com.practice.money.adapter.`in`.web.dto

import com.practice.money.application.port.`in`.command.IncreaseMemberMoneyCommand

data class IncreaseMemberMoneyRequest(
    val membershipId: String,
    val amount: Int
) {
    fun toCommand(): IncreaseMemberMoneyCommand =
        IncreaseMemberMoneyCommand(membershipId, amount)
}
