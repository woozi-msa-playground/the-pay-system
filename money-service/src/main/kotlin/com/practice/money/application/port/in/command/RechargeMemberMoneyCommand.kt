package com.practice.money.application.port.`in`.command

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class RechargeMemberMoneyCommand(
    @field:NotNull
    val membershipId: String,

    @field:NotNull
    val moneyBalance: Int

) : SelfValidating<RechargeMemberMoneyCommand>() {
    init {
        this.validateSelf()
    }
}
