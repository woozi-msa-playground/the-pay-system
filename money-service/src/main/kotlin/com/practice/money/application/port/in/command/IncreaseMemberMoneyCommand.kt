package com.practice.money.application.port.`in`.command

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class IncreaseMemberMoneyCommand(
    @field:NotNull
    val membershipId: String,

    @field:NotNull
    val moneyBalance: Int

) : SelfValidating<IncreaseMemberMoneyCommand>() {
    init {
        this.validateSelf()
    }
}