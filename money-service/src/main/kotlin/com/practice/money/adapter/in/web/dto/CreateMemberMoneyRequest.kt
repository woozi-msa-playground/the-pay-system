package com.practice.money.adapter.`in`.web.dto

import com.practice.money.application.port.`in`.command.CreateMemberMoneyCommand

data class CreateMemberMoneyRequest(
    val membershipId: String
) {
    fun toCommand(): CreateMemberMoneyCommand =
        CreateMemberMoneyCommand(membershipId)
}
