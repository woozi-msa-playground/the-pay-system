package com.practice.money.adapter.axon.command

import com.practice.common.SelfValidating
import jakarta.validation.constraints.NotNull
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class MemberMoneyIncreaseCommand(
    @TargetAggregateIdentifier var id: String? = null,
    @NotNull var membershipId: String? = null,
    @NotNull var balance: Int? = null,
): SelfValidating<MemberMoneyIncreaseCommand>() {
    init {
        this.validateSelf()
    }
}