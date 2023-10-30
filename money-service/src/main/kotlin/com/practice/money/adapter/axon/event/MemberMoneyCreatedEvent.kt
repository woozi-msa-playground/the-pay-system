package com.practice.money.adapter.axon.event

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class MemberMoneyCreatedEvent(
    @NotNull val membershipId: String
): SelfValidating<MemberMoneyCreatedEvent>() {
    init {
        this.validateSelf()
    }
}