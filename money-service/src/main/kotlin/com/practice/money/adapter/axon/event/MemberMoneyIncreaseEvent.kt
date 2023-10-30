package com.practice.money.adapter.axon.event

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class MemberMoneyIncreaseEvent(
    @NotNull var id: String? = null,
    @NotNull var membershipId: String? = null,
    @NotNull var balance: Int? = null,
): SelfValidating<MemberMoneyIncreaseEvent>() {
    init {
        this.validateSelf()
    }
}