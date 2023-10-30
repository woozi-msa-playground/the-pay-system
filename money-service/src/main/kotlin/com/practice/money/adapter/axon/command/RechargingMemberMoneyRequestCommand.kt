package com.practice.money.adapter.axon.command

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RechargingMemberMoneyRequestCommand(
    @TargetAggregateIdentifier var aggregateIdentifier: String,
    @NotNull val rechargingRequestId: String,
    @NotNull val membershipId: String,
    @NotNull val amount: Int,
): SelfValidating<RechargingMemberMoneyRequestCommand>() {
    init {
        this.validateSelf()
    }
}