package com.practice.money.application.port.`in`.command

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class CreateMemberMoneyCommand(
    @NotNull val membershipId: String
): SelfValidating<CreateMemberMoneyCommand>() {
    init {
        this.validateSelf()
    }
}