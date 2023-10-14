package com.practice.money.adapter.`in`.web

import com.practice.money.application.port.`in`.IncreaseMemberMoneyCommand

data class IncreaseMemberMoneyRequest(
    val membershipId: String,
    val amount: Int
) {
    fun toCommand(): IncreaseMemberMoneyCommand =
        IncreaseMemberMoneyCommand(membershipId, amount)
}
