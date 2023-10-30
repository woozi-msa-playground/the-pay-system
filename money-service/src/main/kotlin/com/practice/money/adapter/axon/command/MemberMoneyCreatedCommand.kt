package com.practice.money.adapter.axon.command

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class MemberMoneyCreatedCommand(
    @NotNull val membershipId: String
): SelfValidating<MemberMoneyCreatedCommand>() {
    init {
        this.validateSelf()
    }
}